import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Yifei Pei with the student ID a1611648
 *
 */

public class FsaReaderWriter implements FsaIo 
{
	private Fsa fsa;
	private Reader reader;
	private Writer writer;
	private int inputType;
	private final int STATE = 1;
	private final int TRANSITION = 2;
	private final int INITIAL = 3;
	private final int FINAL = 4;
	private final int OTHERS = 5;

	FsaReaderWriter()
	{
		fsa = null;
		reader = null;
		writer = null;
		inputType = 0;
	}

	@Override
	public void read(Reader r, Fsa f) throws IOException, FsaFormatException 
	{
		reader = r;
		fsa = f;
		BufferedReader br = null;

		try {
			String line;
			String temp;
			String [] Temp;
			int Line = 1;
			String message = "Incorrect Format of Fsa";
			br = new BufferedReader (reader);

			while ((line = br.readLine()) != null) {
				temp = line;
				Temp = temp.split(" ");

				if (Temp[0].contains("state") && (Temp.length == 4)) {
					inputType = STATE;
				} else if (Temp[0].contains("transition") && (Temp.length == 4)) {
					inputType = TRANSITION;
				} else if (Temp[0].contains("initial") && (Temp.length == 2)) {
					inputType = INITIAL;
				} else if (Temp[0].contains("final") && (Temp.length == 2)) {
					inputType = FINAL;
				} else if (Temp[0].contains("#") || temp.length() == 0) {
					inputType = OTHERS;
				} else {
					throw new FsaFormatException(Line, message);
				}

				switch (inputType) {
				case STATE:
					String stateName = Temp[1];
					int x;
					int y;
					if (isLegalStateName(stateName) != true)
						throw new FsaFormatException(Line, message);
					if ((isLegalPosition(Temp[2]) != true) || (isLegalPosition(Temp[3]) != true))
						throw new FsaFormatException(Line, message);
					x = Integer.parseInt(Temp[2]);
					y = Integer.parseInt(Temp[3]);
					fsa.newState(stateName, x, y);
					break;

				case TRANSITION:
					State FromState = null;
					State ToState = null;
					String fromState = Temp[1]; 
					String eventName = Temp[2];
					String toState = Temp[3];
					for (State s: fsa.getStates()) {
						if (s.getName().equals(fromState))
							FromState = s;
						if (s.getName().equals(toState))
							ToState = s;
					}
					if (isLegalEventName(eventName) != true)
						throw new FsaFormatException(Line, message);
					if ((FromState == null) || (ToState == null))
						throw new FsaFormatException(Line, message);
					if (eventName.equals("?"))
						eventName = "";
					fsa.newTransition(FromState, ToState, eventName); 
					break;

				case INITIAL:
					State InitialState = null;
					String initialState = Temp[1];
					InitialState = fsa.findState(initialState);
					if (InitialState == null) {
						throw new FsaFormatException(Line, message);
					} else {
						InitialState.setInitial(true);
					}
					break;

				case FINAL:
					State FinalState = null;
					String finalState = Temp[1];
					FinalState = fsa.findState(finalState);
					if (FinalState == null) {
						throw new FsaFormatException(Line, message);
					} else {
						FinalState.setFinal(true);
					}
					break;

				case OTHERS:

					break;

				default:
					throw new FsaFormatException(Line, message);
				}
				Line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

	@Override
	public void write(Writer w, Fsa f) throws IOException 
	{
		writer = w;
		fsa = f;
		String output = fsa.toString();
		writer.write(output);
		w.flush();
	}

	public boolean isLegalStateName (String name) {
		Pattern p = Pattern.compile("^[a-zA-Z]+(\\d|_|[a-zA-Z])*");
		Matcher m = p.matcher(name);
		return m.matches();
	}

	public boolean isLegalEventName (String name) {
		if (name.equals("?")) {
			return true;
		} else {
			Pattern p = Pattern.compile("[a-zA-Z]*");
			Matcher m = p.matcher(name);
			return m.matches();
		}
	}

	public boolean isLegalPosition (String pos) {
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(pos);
		return m.matches();
	}

}
