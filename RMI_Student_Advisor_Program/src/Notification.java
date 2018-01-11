/*
 * Name:- Tanmay Manohar Borkar
 * ID: 1001503737
 * Lab Assignment - 2
 * -----------------------------------------------------
 * 
 * The program is a client application that acts as notification process that allows the client to get the decision given by the Advisor. The client will check for notifications after every 8 seconds and display them if there are any
 * 
 * Steps to run the Notification on command line:-
 * 1. Open Command Prompt.
 * 2. Open the directory where the project is stored.
 * 3. Execute the following command "java AppServer"
 * 4. The program execution will start.
 * 
 * Reference:
 * - https://www.javatpoint.com/RMI
 * - https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 
 */
import java.rmi.Naming;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Notification {
	public static void main(String args[]) {
		while(true) {
			try {
				MessageQueingServiceImpl notificationClient = (MessageQueingServiceImpl)Naming.lookup("rmi://localhost:5000/MessageQueue");		//Connect to RMI Server
				Map<String, List<String>> notifications = notificationClient.studentNotification();		//Call to remote method to get notfications
				
				if(notifications.isEmpty()) {			//Display notification message if map is empty
					System.out.println("\nNo new Notifications");
				} else {		//Display decisions
					System.out.println("Name\tCourse\tDecision");
					for (Map.Entry<String, List<String>> entry : notifications.entrySet()) {
						List <String> values = entry.getValue();
						System.out.println(entry.getKey() + "\t" + values.get(0) + "\t" + values.get(1));
					}
				}
				TimeUnit.SECONDS.sleep(8);		//Check for notifications every 8 seceonds
			} catch(Exception e) {
				System.out.println("Notification: " + e);
			}

		}
	}
}
