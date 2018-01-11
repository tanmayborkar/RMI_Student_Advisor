/*
 * Name:- Tanmay Manohar Borkar
 * ID: 1001503737
 * Lab Assignment - 2
 * -----------------------------------------------------
 * 
 * The program is a client application that acts as notification process that allows the client to get the decision given by the Advisor. The client will check for notifications after every 8 seconds and display them if there are any
 * 
 * Steps to run the Advisor on command line:-
 * 1. Open Command Prompt.
 * 2. Open the directory where the project is stored.
 * 3. Execute the following command "java Advisor"
 * 4. The program execution will start.
 * 
 * Reference:
 * - https://www.javatpoint.com/RMI
 * - https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Advisor {

	public static void main(String args[]) {
		while(true) {
			try {
				MessageQueingServiceImpl advisorClient = (MessageQueingServiceImpl)Naming.lookup("rmi://localhost:5000/MessageQueue");	//Connect to RMI Server	
				InputStreamReader in = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(in);
				Map<String, List<String>> map = new LinkedHashMap<>();
				
				LinkedHashMap<String, String> notifications = advisorClient.advisorMessage();
				
				if(notifications.containsKey("Empty")) {	//If map is empty display no notification message
					System.out.println("\n" + notifications.get("Empty"));
				} else {	//Get the decision if there are any messages
				for(Map.Entry<String, String> notification : notifications.entrySet()) {
					System.out.println(notification.getKey() + " needs clearence for " + notification.getValue() + ". Approve?(Yes/No)");
					String Approved = br.readLine();
					if(Approved.isEmpty() || Approved.equals(" ")) {
						System.out.println("Decision cannot be empty\nDecision:- ");
						Approved = br.readLine();
					}

					List<String> courseDecision = new ArrayList<>();
					courseDecision.add(notification.getValue());
					courseDecision.add(Approved);
					map.put(notification.getKey(), courseDecision);
				}
		
				System.out.println(advisorClient.advisorDecisionWrite(map));	//Store decision and display success message
				}
				TimeUnit.SECONDS.sleep(3);		//Repeat after 3 seconds
			} catch(Exception e) {
				System.out.println("Advisor:" + e);
			}
			
		}
	}
}
