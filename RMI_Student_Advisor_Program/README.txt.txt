Name:- Tanmay Manohar Borkar
ID: 1001503737
Lab Assignment - 2
-----------------------------------------------------


The program is an RMI application. It makes use of the Java RMI to allow multiple clients to invoke methods on the server. It consists of three processes Student, Advisor and Notifications. The Student process asks the Advisor for clearance to certain courses. The Advisor will then provide decision for each student on the advisor client. The notification process will then display the advisor decision.

Steps to run the RMI stub on command line:-
1. Open Command Prompt.
2. Open the directory where the project is stored.
3. Compile the all the java files using "javac *.java"
4. Execute the following command "java AppServer"Create the stub and skeleton objects using "rmic MessageQueingServiceRemote".
5. The program execution will start.

Steps to run the Server on command line:-
1. Open Command Prompt.
2. Open the directory where the project is stored.
3. Execute the following command "java MessageQueingServiceServer"
4. The program execution will start.

Steps to run the Advisor command line:-
1. Open Command Prompt.
2. Open the directory where the project is stored.
3. Execute the following command "java Advisor"
4. The program execution will start.

Steps to run the Student on command line:-
1. Open Command Prompt.
2. Open the directory where the project is stored.
3. Execute the following command "java Student"
4. The program execution will start.

Steps to run the Notification on command line:-
1. Open Command Prompt.
2. Open the directory where the project is stored.
3. Execute the following command "java Notification"
4. The program execution will start.

Reference:
- https://www.javatpoint.com/RMI
- https://docs.oracle.com/javase/tutorial/rmi/index.html
