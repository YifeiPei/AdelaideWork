/*
 * sr.c
 *
 *      Author: Yifei
 *      a1611648
 */

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "emulator.h"
#include "sr.h"

#define RTT  15.0       /* round trip time.  MUST BE SET TO 15.0 when submitting assignment */
#define WINDOWSIZE 6    /* Maximum number of buffered unacked packet */
#define SEQSPACE 12     /* sequence space for SR is double of windowsize */
#define NOTINUSE (-1)   /* used to fill header fields that are not being used */

/* generic procedure to compute the checksum of a packet.  Used by both sender and receiver
   the simulator will overwrite part of your packet with 'z's.  It will not overwrite your
   original checksum.  This procedure must generate a different checksum to the original if
   the packet is corrupted.
*/
int ComputeChecksum(struct pkt packet)
{
  int checksum = 0;
  int i;

  checksum = packet.seqnum;
  checksum += packet.acknum;
  for ( i=0; i<20; i++ )
    checksum += (int)(packet.payload[i]);

  return checksum;
}

bool IsCorrupted(struct pkt packet)
{
  if (packet.checksum == ComputeChecksum(packet))
    return (false);
  else
    return (true);
}


/********* Sender (A) variables and functions ************/

static struct pkt A_buffer[WINDOWSIZE];  /* array for storing packets waiting for ACK */
static int A_windowfirst, A_windowlast;  /* array indexes of the first/last packet awaiting ACK */
static int A_windowcount;                /* the number of packets currently awaiting an ACK */
static int A_nextseqnum;                 /* the next sequence number to be used by the sender */

static int ACKed[WINDOWSIZE];            /* array for storing packets that have received ACK */

/* the following routine will be called once (only) before any other */
/* entity A routines are called. You can use it to do any initialization */
void A_init(void)
{
  /* initialise A's window, buffer and sequence number */
  A_nextseqnum = 0;  /* A starts with seq num 0, do not change this */
  A_windowfirst = 0;
  A_windowlast = -1;   /* windowlast is where the last packet sent is stored.
		     new packets are placed in winlast + 1
		     so initially this is set to -1
		   */
  A_windowcount = 0;
}

/* called from layer 5 (application layer), passed the message to be sent to other side */
void A_output(struct msg message)
{
  struct pkt sendpkt;
  int i;

  /* if not blocked waiting on ACK */
  if (A_windowcount < WINDOWSIZE) {
    if (TRACE > 1)
      printf("----A: New message arrives, send window is not full, send new messge to layer3!\n");

    /* create packet */
    sendpkt.seqnum = A_nextseqnum;
    sendpkt.acknum = NOTINUSE;
    for ( i=0; i<20 ; i++ )
      sendpkt.payload[i] = message.data[i];
    sendpkt.checksum = ComputeChecksum(sendpkt);

    /* put packet in window buffer */
    A_windowlast = (A_windowlast + 1) % WINDOWSIZE;
    A_buffer[A_windowlast] = sendpkt;
    A_windowcount++;

    /* all non ACKed packets in window are signed by '0'*/
    ACKed[A_windowlast] = 0;

    /* send out packet */
    if (TRACE > 0)
      printf("Sending packet %d to layer 3\n", sendpkt.seqnum);
    tolayer3 (A, sendpkt);

    /* start timer if first packet in window */
    if (A_windowcount == 1)
      starttimer(A,RTT);

    /* get next sequence number, wrap back to 0 */
    A_nextseqnum = (A_nextseqnum + 1) % SEQSPACE;
  }
  /* if blocked,  window is full */
  else {
    if (TRACE > 0)
      printf("----A: New message arrives, send window is full\n");
    window_full++;
  }
}

/* called from layer 3, when a packet arrives for layer 4
   In this practical this will always be an ACK as B never sends data.
*/
void A_input(struct pkt packet)
{
  int ackcount = 0;

  int seqcount;
  int expectedACK;

  /* if received ACK is not corrupted */
  if (!IsCorrupted(packet)) {
    if (TRACE > 0)
      printf("----A: uncorrupted ACK %d is received\n",packet.acknum);
    total_ACKs_received++;

    /* mark pkt as received
     * if the pkt is smallest num unACKed pkt, advance window base to next unACKed seqnum */

    expectedACK = A_nextseqnum - A_windowcount;
    if (expectedACK<0) {
      expectedACK = expectedACK + SEQSPACE;
    }

    seqcount = packet.acknum - expectedACK;
    if (seqcount<0) {
      seqcount = seqcount + SEQSPACE;
    }

    /* check if new ACK or duplicate*/
    if ((A_windowcount > 0) && (seqcount < WINDOWSIZE)) {

      /* packet is a new ACK */
      if (TRACE > 0)
        printf("----A: ACK %d is not a duplicate\n",packet.acknum);
      new_ACKs++;

      ackcount = (seqcount + A_windowfirst) % WINDOWSIZE;
      ACKed[ackcount] = NOTINUSE;

      /* slide window in sr */
      while ((ACKed[A_windowfirst] == NOTINUSE) && (A_windowcount >0)) {

    	/* slide window by the number of packets ACKed */
        A_windowfirst = (A_windowfirst+1) % WINDOWSIZE;

        /* delete the acked packets from window buffer */
    	A_windowcount--;
      }

      /* start timer again if there are still more unacked packets in window */
      if (seqcount == 0) {
        stoptimer(A);
    	if (A_windowcount > 0)
    	  starttimer (A,RTT);
      }
    } else
    	if (TRACE > 0)
    	  printf ("----A:duplicate ACK received, do nothing!\n");
  } else
	  if (TRACE>0)
	    printf ("----A:corrupted ACK is received, do nothing!\n");
}

/* called when A's timer goes off */
void A_timerinterrupt(void)
{
  if (TRACE > 0)
    printf("----A: time out,resend packets!\n");

  if (TRACE > 0)
    printf ("---A: resending packet %d\n", (A_buffer[A_windowfirst]).seqnum);

  /* only resend the oldest packet in window*/
  tolayer3 (A,A_buffer[A_windowfirst]);
  packets_resent++;
  if (A_windowcount > 0)
      starttimer(A,RTT);
}

/********* Receiver (B)  variables and procedures ************/

static int expectedseqnum; /* the sequence number expected next by the receiver */
static int B_nextseqnum;   /* the sequence number for the next packets sent by B */

static struct pkt B_buffer[WINDOWSIZE];  /* array to store buffered packets in B */
static int B_windowfirst;           /* the index of the first packet in B_buffer */

/* the following routine will be called once (only) before any other */
/* entity B routines are called. You can use it to do any initialization */
void B_init(void)
{
  expectedseqnum = 0;
  B_nextseqnum = 1;

  int i;
  for (i=0;i<WINDOWSIZE;i++) {
	  B_buffer[i].seqnum = NOTINUSE;
  }
  B_windowfirst = 0;
}

/* called from layer 3, when a packet arrives for layer 4 at B*/
void B_input(struct pkt packet)
{
  struct pkt sendpkt;
  int i;

  int seqcount;

  /* if not corrupted */
  if  ( (!IsCorrupted(packet)) ) {
    if (TRACE > 0)
      printf("----B: packet %d is correctly received, send ACK!\n",packet.seqnum);
    packets_received++;

    /* buffer out-of-order packets
     * deliver ACKs of any in-order packets, no matter buffered or not
     * advance window to next not-yet-received packet */

    seqcount = packet.seqnum - expectedseqnum;
    if (seqcount < 0)
      seqcount = seqcount + SEQSPACE;

    if (seqcount < WINDOWSIZE) {
      B_buffer[((B_windowfirst + seqcount) % WINDOWSIZE)] = packet;
      while (B_buffer[B_windowfirst].seqnum != NOTINUSE) {

    	/* deliver to receiving application */
    	tolayer5(B, B_buffer[B_windowfirst].payload);

    	B_buffer[B_windowfirst].seqnum = NOTINUSE;
    	B_windowfirst = (B_windowfirst+1) % WINDOWSIZE;

    	/* update state variables */
    	expectedseqnum = (expectedseqnum+1) % SEQSPACE;
      }
    }

    /* create packet */

    /* send an ACK for the received packet */
    sendpkt.acknum = packet.seqnum;

    sendpkt.seqnum = B_nextseqnum;
    B_nextseqnum = (B_nextseqnum + 1) % 2;

    /* we don't have any data to send.  fill payload with 0's */
    for ( i=0; i<20 ; i++ )
      sendpkt.payload[i] = '0';

    /* computer checksum */
    sendpkt.checksum = ComputeChecksum(sendpkt);

    /* send out packet */
    tolayer3 (B, sendpkt);
  }
  /* otherwise do nothing*/
}

/******************************************************************************
 * The following functions need be completed only for bi-directional messages *
 *****************************************************************************/

/* Note that with simplex transfer from a-to-B, there is no B_output() */
void B_output(struct msg message)
{
}

/* called when B's timer goes off */
void B_timerinterrupt(void)
{
}
