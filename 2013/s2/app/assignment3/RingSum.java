
/*
Assignment 3 for Advanced Programming Paradigms
Semester 2, 2013
*/

/**
 * @filename RingSum.java
 * @author Yifei Pei a1611648
 */

import java.util.Random;
import mpi.*;

public class RingSum {
	
	/**
	 * The main for the assignment to execute two questions: 
	 * 1. Passing numbers around a ring
	 * 2. Parallel sum of those numbers
	 * @param args pass arguments to initialise MPI to set up 100 processes
	 * In terminal, it should be
	 * "mpjrun.sh -np 100 RingSum"
	 * In Eclipse, the configuration should specify
	 * "-jar ${MPJ_HOME}/lib/starter.jar -np 100"
	 * in VM argument
	 * @throws Exception
	 */
	
	/*
	 * set up tags for MPI operations
	 * 0 to PASS for passing numbers in the ring buffer
	 * 1 to SUM for summing up the numbers in the buffer 
	 */
	public final static int PASS = 0;
	public final static int SUM = 1;
	/*
	 *  the designated number of processes to do the sum is 5
	 *  according to the assignment specification
	 */
	public final static int PROC = 5;  
	
	public static void main(String[] args) throws Exception {
		// initialise the whole program to get rank and size depending on args
		MPI.Init(args); //According to assignment specification, the args should set up 100 processes
		int rank = MPI.COMM_WORLD.Rank(); //generate all the nodes and get indexes
		int size = MPI.COMM_WORLD.Size(); //the number of all the nodes
		
		int [] buf = new int[size]; //the buffer to store all the data for different processes
		
		/*
		 *  implement the ring buffer:
		 *  for loop: from head node to tail node and then back to head node.
		 *  
		 *  1. Starting at process zero send a message around the ring and returning to process zero.
		 *  The message will contain a buffer of size 100, 
		 *  and will collect one additional number from each process as it travels around the ring. 
		 *  When it arrives back at process zero, there will be 100 numbers in the buffer,
		 *  one from each process.
		 *  
		 *  2. print out these numbers.
		 */
		for (int count=0;count<=size;count++) {
			/*
			 * Finally the loop will go back to the head node,
			 * so (count <= size) not (count < size)
			 * also mod to make sure the process to continue to the head again
			 */
			if (rank == count%size) {
				if (count != 0) {
					// if not the starting point, receive the message sent by the previous node
					MPI.COMM_WORLD.Recv(buf, 0, count, MPI.INT, count-1, PASS);
				}
				if (count < size) {
					// if not go back to the head, send random number to next node
					buf[count] = seeding(count); //seeding function to generate pseudo-random number
					MPI.COMM_WORLD.Send(buf, 0, count+1, MPI.INT, (count+1)%size, PASS); //pass the newly generated number to next node
				} else {
					// when go back to the head, ring buffering ends and print all the numbers
					System.out.println("Ring buffering finished, the buffered numbers are printed below:");
					printBuf (buf); //printBuf function to print the buffer in appropriate format
				}
			}
		}
		
		// A new phase of MPI processing starts, separate from previous works
		MPI.COMM_WORLD.Barrier();
		
		/*
		 * sum the buffer by using 5 processes:
		 * Choose 5 processes among all of them to do the work:
		 * distributed to Process 0, 20, 40, 60, and 80.
		 * 
		 * 1. Because Process 0 is the supervisor, 
		 * finally it will sum up all the results generated by itself and other 4 processes.
		 * 
		 * 2. Each working process will deal with equal amount of numbers,
		 * so the workload can be balanced.
		 */
		
		// To balance the workload for different processes, divide the workload by 5
		int aloc = size/PROC;
		int sum = 0; //the temp buffer to store the sum of numbers
		
		if (rank == 0) { //Process 0 is the master node to allocate works for others
			// Do the work for itself, sum up the numbers from 0 to 19
			for (int i=0;i<aloc;i++) {
                sum += buf[i];
            }
			
			// Assign partial sum by head node to buf[0], which is the buffer storage for Process 0
			buf[0] = sum;
			System.out.println("The Partial Sum of Process " + rank + ": is " + buf[0]);
			
			// Allocate work for other 4 processes
			for (int i=0+aloc;i<size;i=i+aloc) { //allocate work to Process 20, 40, 60, and 80
				// every process is sent a chunk of 20 numbers (size/5)
                MPI.COMM_WORLD.Send(buf, i, aloc, MPI.INT, i, SUM);
			}
		}
		
		// The rest 4 processes do their works, to sum up the numbers allocated to them
		for (int i=0+aloc;i<size;i=i+aloc) { //i=20, 40, 60, 80
			if (rank == i) {
				MPI.COMM_WORLD.Recv(buf, i, aloc, MPI.INT, 0, SUM); //receive all the numbers allocated by supervisor
				sum = 0; //initialise the temp buffer to 0, so we can use it later
				for (int j=0;j<aloc;j++) { //sum up all the allocated numbers
                    sum += buf[i+j];
                }
				// assign partial sum to the designated place
				buf[i] = sum;
				System.out.println("The Partial Sum of Process " + rank + ": is " + buf[i]);
				// send back information to supervisor so it can sum up all the partial sums
				MPI.COMM_WORLD.Send(buf, i, 1, MPI.INT, 0, SUM);
			}
		}
		
		if (rank == 0) { //Process 0 is the master node to sum up all the partial sums finally
			sum = buf[0]; //initialise the temp buffer so the final sum can be calculated
			for (int i=0+aloc;i<size;i=i+aloc) {
				// Receive all the information sent by the other 4 processes
				MPI.COMM_WORLD.Recv(buf, i, 1, MPI.INT, i, SUM);
                sum += buf[i]; //get the final sum
			}
			System.out.println("The Final Sum is: " + sum);
		}
		
		MPI.Finalize(); //close MPI communication
	}
	
	/**
	 * The seeding function to generate random number by the transfered seed
	 * @param seed the seed for the random generator, should be the rank of the process
	 * @return the generated random number
	 */
	public static int seeding (int seed) {
		
		Random n = new Random (seed); //the transfered parameter should be the rank of the process
		
		int i = n.nextInt(); //generate random number
		// get the absolute value
		if (i < 0) {
			i = Math.abs(i);
		}
		// if larger than 200, mod
		if (i > 200) {
			i = i%200;
		}
		//System.out.println(i);
		
		return i;
	}
	
	/**
	 * print the buffered value
	 * @param buf the buffer that store all the values generated by all the processes
	 */
	public static void printBuf (int [] buf) {
		System.out.print("[ ");
		for (int i=0;i<buf.length;i++) {
			if (i == buf.length -1) {
				System.out.print (buf[i] + " ");
			} else {
				System.out.print (buf[i] + ", ");
			}
		}
		System.out.println(" ]");
	}

}
