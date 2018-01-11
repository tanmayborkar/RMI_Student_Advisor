/*
 * Name:- Tanmay Manohar Borkar
 * ID: 1001503737
 * Lab Assignment - 2
 * -----------------------------------------------------
 * 
 * The program is a client application that acts as student process that allows the client to ask for clearance for a course from the Advisor. The client will enter student name and course name for which they need clearance. The client can continue to add names until process is stopped.
 * 
 * Steps to run the Student on command line:-
 * 1. Open Command Prompt.
 * 2. Open the directory where the project is stored.
 * 3. Execute the following command "java Student"
 * 4. The program execution will start.
 * 
 * Reference:
 * - https://www.javatpoint.com/RMI
 * - https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
public class Student {

	public static void main(String args[]) {
		try {
			MessageQueingServiceImpl studentClient = (MessageQueingServiceImpl)Naming.lookup("rmi://localhost:5000/MessageQueue");		//Connect to RMI Server
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);
			while(true) {
				System.out.println("\nEnter Student Name:- ");
				String student = br.readLine();
				System.out.println("\nEnter Course Name:- ");
				String course = br.readLine();
				System.out.println(studentClient.studentMessage(student,course));		//Call to remote method to store inputs
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}	
}
