import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Open an SMTP connection to a mailserver and send one mail.
 *
 * Modified by Yifei Pei a1611648
 */

public class ESMTPConnection {

	/* The socket to the server */
	private Socket connection;

	/* Streams for reading and writing the socket */
	private BufferedReader fromServer;
	private DataOutputStream toServer;

	/* Use the default port ONLY if user doesn't specify port in server field - server:port */
	private static final int DEFAULT_SMTP_PORT = 25; 
	private static final String CRLF = "\r\n";

	/* Are we connected? Used in close() to determine what to do. */
	private boolean isConnected = false;

	/* Create an ESMTPConnection object. Create the socket and the 
       associated streams. Initialize ESMTP connection. */
	public ESMTPConnection(Envelope envelope) throws Exception {

		connection = new Socket (envelope.DestHost, DEFAULT_SMTP_PORT);
		fromServer = new BufferedReader (new InputStreamReader(connection.getInputStream()));
		toServer =  new DataOutputStream (connection.getOutputStream());

		String InFromServer = "";
		int rc = 0;
		/* Read a line from server and check that the reply code is correct.
           If not, throw an IOException. */
		InFromServer = fromServer.readLine();
		rc = parseReply (InFromServer);
		//System.out.println(rc);
		if (rc != 220)
			throw new IOException ();
		//System.out.println("Mail server ".concat(envelope.DestHost).concat("found."));

		/* ESMTP handshake. We need the name of the local machine.
           Send the appropriate ESMTP handshake command. */
		//InetAddress host = InetAddress.getLocalHost();
		String localhost = (InetAddress.getLocalHost()).getCanonicalHostName();
		//System.out.println("LOCALHOST: " + localhost);
		sendCommand ("EHLO ".concat(localhost), 250);

		isConnected = true;
	}

	/* Send the message. Write the correct ESMTP-commands in the
       correct order. No checking for errors, just throw them to the
       caller. */
	public void send(Envelope envelope) throws Exception {
		String mailFrom = "MAIL FROM: ".concat(envelope.Sender);
		String rcptTo = "RCPT TO: ".concat(envelope.Recipient);
		String data = "DATA";
		String sendMessage = envelope.Message.toString().concat(CRLF).concat(".");
		
		/* Send all the necessary commands to send a message. Call
              sendCommand() to do the dirty work. Do _not_ catch the
              exception thrown from sendCommand(). */
		sendCommand (mailFrom, 250);
		sendCommand (rcptTo, 250);
		sendCommand (data, 354);
		sendCommand (sendMessage, 250);
	}

	/* Close the connection. First, terminate on ESMTP level, then
       close the socket. */
	public void close() {
		isConnected = false;
		try {
			sendCommand ("QUIT", 221);
			/*close socket */
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable to close connection: " + e);
			isConnected = true;
		}
	}

	/* Send an ESMTP command to the server. Check that the reply code is
       what is is supposed to be according to RFC 821. */
	private void sendCommand(String command, int rc) throws Exception {

		String InFromServer = "";
		int replyCode = 0;
		/* Write command to server and read reply from server. */
		//System.out.println("Command to server: " + command);
		command = command.concat(CRLF);
		toServer.writeBytes(command);

		fromServer = new BufferedReader (new InputStreamReader (connection.getInputStream()));
		InFromServer = fromServer.readLine();
		replyCode = parseReply (InFromServer);
		//System.out.println("Reply from server: " + InFromServer);
		/* Check that the server's reply code is the same as the parameter
        rc. If not, throw an IOException. */
		if (replyCode != rc) {
			//System.out.println("Reply from server: " + InFromServer);
			//throw new IOException ();
			
			// try to do error checking
			if (replyCode == 500) {
				System.out.println("Syntax error, command unrecognized");
				throw new Exception ();
			} else if (replyCode == 501) {
				System.out.println("Syntax error in parameters or arguments");
				throw new Exception ();
			} else if (replyCode == 502) {
				System.out.println("Command not implemented");
				throw new IOException ();
			} else if (replyCode == 503) {
				System.out.println("Bad sequence of commands");
				throw new IOException ();
			} else if (replyCode == 504) {
				System.out.println("Command parameter not implemented");
				throw new IOException ();
			} else if (replyCode == 421) {
				System.out.println("Service not available, closing transmission channel");
				throw new IOException ();
			} else if (replyCode == 455) {
				System.out.println("Server unable to accommodate parameters");
				throw new IOException ();
			} else if (replyCode == 555) {
				System.out.println("MAIL FROM/RCPT TO parameters not recognized or not implemented");
				throw new Exception ();
			} else if (replyCode == 450) {
				System.out.println("Requested mail action not taken: mailbox unavailable");
				throw new IOException ();
			} else if (replyCode == 550) {
				System.out.println("Requested action not taken: mailbox unavailable");
				throw new IOException ();
			} else if (replyCode == 451) {
				System.out.println("Requested action aborted: error in processing");
				throw new IOException ();
			} else if (replyCode == 551) {
				System.out.println("User not local; please try <forward-path>");
				throw new Exception ();
			} else if (replyCode == 452) {
				System.out.println("Requested action not taken: insufficient system storage");
				throw new IOException ();
			} else if (replyCode == 552) {
				System.out.println("Requested mail action aborted: exceeded storage allocation");
				throw new IOException ();
			} else if (replyCode == 553) {
				System.out.println("Requested action not taken: mailbox name not allowed");
				throw new Exception ();
			} else if (replyCode == 554) {
				System.out.println("Transaction failed");
				throw new IOException ();
			} else if (replyCode == 252) {
				System.out.println("Cannot VRFY user, but will accept message and attempt delivery");
				throw new Exception ();
			} else if (replyCode == 251) {
				System.out.println("User not local; will forward to <forward-path>");
				throw new Exception ();
			} else {
				throw new IOException ();
			}
		}
	}

	/* Parse the reply line from the server. Returns the reply code. */
	private int parseReply(String reply) {
		int rc = 0;
		String tmp = "";
		//tmp = reply.substring(0,3);
		String token [] = reply.split(" ");
		String etoken [] = token[0].split("-");
		tmp = etoken [0];
		try {
			//System.out.println("reply code is " + etoken[0]);
			rc = Integer.parseInt(tmp);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rc;
	}

	/* Destructor. Closes the connection if something bad happens. */
	protected void finalize() throws Throwable {
		if(isConnected) {
			close();
		}
		super.finalize();
	}
}