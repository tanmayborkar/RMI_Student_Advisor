/*
 * Name:- Tanmay Manohar Borkar
 * ID: 1001503737
 * Lab Assignment - 2
 * -----------------------------------------------------
 * 
 * The program is a server application that acts as the RMI server. All the client processes will connect to this server to invoke the remote method.
 * 
 * Steps to run the Server on command line:-
 * 1. Open Command Prompt.
 * 2. Open the directory where the project is stored.
 * 3. Execute the following command "java MessageQueingServiceServer"
 * 4. The program execution will start.
 * 
 * Reference:
 * - https://www.javatpoint.com/RMI
 * - https://docs.oracle.com/javase/tutorial/rmi/index.html
 */
import java.rmi.*;
import java.rmi.registry.*;
public class MessageQueingServiceServer  {
	public static void main(String args[]) {
		
		try {
				MessageQueingServiceImpl server = new MessageQueingServiceRemote();		//Create a constructor for remote class with required methods

				Naming.rebind("rmi://localhost:5000/MessageQueue",server);		//Bind to rmi stub
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
