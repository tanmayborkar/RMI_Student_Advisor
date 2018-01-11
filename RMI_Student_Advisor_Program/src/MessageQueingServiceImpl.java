/*
 * Name:- Tanmay Manohar Borkar
 * ID: 1001503737
 * Lab Assignment - 2
 * -----------------------------------------------------
 * 
 * The program is the RMI interface, methods defined in here will be implemented by the RMI stub.
 * 
 * Reference:
 * - https://www.javatpoint.com/RMI
 * - https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 
 */
import java.rmi.*;
import java.util.*;  

public interface MessageQueingServiceImpl extends Remote{
	
	public String studentMessage (String Name, String CourseName) throws RemoteException;	//This method is used to store the messages from the student in the file
	public LinkedHashMap<String, String> advisorMessage () throws RemoteException;	//This method is used to get the messages from the student stored in the file for the user
	public String advisorDecisionWrite (Map<String, List<String>> advisorDecision) throws RemoteException;	//This method is used to store the advisor decision to a file
	public Map<String, List<String>> studentNotification () throws RemoteException;	//This method is used to get the decisions of the advisor and display it in the notification process
	
}
