/*
 * Name:- Tanmay Manohar Borkar
 * ID: 1001503737
 * Lab Assignment - 2
 * -----------------------------------------------------
 * 
 * The program is the RMI server, it consists of all the methods required to be invoked. This class implements the interface with the method definitions and will be compiled as the RMI stub.
 * 
 * Steps to run the RMI on command line:-
 * 1. Open Command Prompt.
 * 2. Open the directory where the project is stored.
 * 3. Compile all the code using "javac *.java"
 * 4. Create the stub and skeleton objects using "rmic MessageQueingServiceRemote".
 * 5. Start the registry service by the rmiregistry tool using "rmiregistry 5000"
 * 6. The program execution will start.
 * 
 * Reference:
 * - https://www.javatpoint.com/RMI
 * - https://docs.oracle.com/javase/tutorial/rmi/index.html
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*; 

public class MessageQueingServiceRemote extends UnicastRemoteObject implements MessageQueingServiceImpl {

	public LinkedHashMap<String, String> notifications = new LinkedHashMap<>();
	public MessageQueingServiceRemote() throws RemoteException {	//Class constructor
		super();
	}

	public LinkedHashMap<String, String> advisorQueue() {	
		File advisorMessages = new File("AdvisorMessages.txt");		//Open the file to read
		
		notifications.clear();
		String [] r = null;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(advisorMessages));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {		//Write data to a map
				r = currentLine.split(";");
				String key = r[0];
				String Value = r[1];
				notifications.put(key, Value);
			}
			PrintWriter pw = new PrintWriter(advisorMessages);		//Clear the file
			br.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(notifications == null || notifications.isEmpty()) {		//Store empty message if no messages
			LinkedHashMap<String, String> emptyNotification = new LinkedHashMap<>();
			emptyNotification.put("Empty", "No Notifications Found");
			return emptyNotification;
		} else {	//Return messages 
			return notifications;
		}
	}


	@Override
	public String studentMessage(String Name, String CourseName) throws RemoteException {	//This method is used to store the messages from the student in the file

		try {

			notifications.put(Name, CourseName);
			File advisorDecision = new File("AdvisorMessages.txt");	//Open the file to store data

			BufferedWriter bw = new BufferedWriter(new FileWriter(advisorDecision,true));

			bw.write(Name + ";" + notifications.get(Name));	//Write data to the file
			bw.newLine();
			bw.close();
			notifications.clear();

		} catch(IOException e) {
			System.out.println(e);
		}
		return "Message Has Been Saved";	//Return success message
	}

	@Override
	public LinkedHashMap<String, String> advisorMessage() throws RemoteException {		//This method is used to get the messages from the student stored in the file for the user
		
		LinkedHashMap<String, String> notification = advisorQueue();	//Get messages into a map
		return notification;	 //return notifications
		
	}

	@Override
	public String advisorDecisionWrite(Map<String, List<String>> advisorDecision ) throws RemoteException {		//This method is used to store the advisor decision to a file

		try {
			File advisorWrite = new File("AdvisorDecision.txt");	//Open the file to write
			BufferedWriter bw = new BufferedWriter(new FileWriter(advisorWrite,true));
			for (Map.Entry<String, List<String>> entry : advisorDecision.entrySet()) {		//Write the decision to the file
				List <String> values = entry.getValue();
				bw.write(entry.getKey() + ";" + values.get(0) + ";" + values.get(1) + ";");
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			return e.toString();
		}

		return "Decision Saved";		//Return Success message
	}

	@Override
	public Map<String, List<String>> studentNotification() throws RemoteException {		//This method is used to get the decisions of the advisor and display it in the notification process
		Map<String, List<String>> notifications = new LinkedHashMap<String, List<String>>();
		File advisorWrite = new File("AdvisorDecision.txt");		//Open the file to read
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(advisorWrite));
			String currentLine;
			String [] r;
			while((currentLine = br.readLine()) != null) {		//Store the contents of the file to a map
				r = currentLine.split(";");
				String student = r[0];
				String course = r[1];
				String decision = r[2];
				List<String> values = new ArrayList<>();
				values.add(course);
				values.add(decision);
				notifications.put(student, values);
				BufferedWriter bw = new BufferedWriter(new FileWriter(advisorWrite));
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return notifications;		//Return the notificatiosn to the calling process
	}


}
