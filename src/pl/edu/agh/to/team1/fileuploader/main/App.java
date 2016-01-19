package pl.edu.agh.to.team1.fileuploader.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pl.edu.agh.to.team1.fileuploader.server.WebSocketServer;
import pl.edu.agh.to.team1.fileuploader.tests.TestClient;
import pl.edu.agh.to.team1.fileuploader.tests.TestCompilerToUploader;
import pl.edu.agh.to.team1.fileuploader.tests.TestUploaderToUser;
import pl.edu.agh.to.team1.fileuploader.tests.TestUserToUploader;
import pl.edu.agh.to.team1.fileuploader.persistence.HibernateUtils;

public class App{
	
	public static void main(String[] args) throws IOException {
		HibernateUtils.getSession().close();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		WebSocketServer webSocketServer = new WebSocketServer();
		ConfigManager confManager = new ConfigManager();
		webSocketServer.start();
		
		while(true){
			System.out.println("FileUploader server [? for help] > :");
			String command = bufferedReader.readLine();
			if (command.startsWith("?"))
			{
				System.out.println("'?'			- print this help");
				System.out.println("'x'			- exit FileUploader");
				System.out.println("'client t1'		- send text test");
				System.out.println("'user2uploader'		- test data transfer from User And Stats to File Uploader");
				System.out.println("'compiler2uploader'	- test data transfer from Compiler to File Uploader");
				System.out.println("'uploader2user' 	- test data transfer from File Uploader to User And Stats");
				System.out.println("'change-key KEY VAL' 	- change value 'VAL' related to 'KEY'");
				System.out.println("'find-value KEY' 	- find value related to 'KEY'");
			}
			else if (command.startsWith("change-key"))
			{
				String[] values = command.split("\\s+");
				confManager.setValue(values[1], values[2]);
			}
			else if (command.startsWith("find-value"))
			{
				
				String[] values = command.split("\\s+");
				System.out.println("KEY= "+values[1]);
				String value = confManager.getValue(values[1].trim());
				System.out.println(value);
			}
			else if (command.equals("client t1"))
			{
				TestClient client = new TestClient();
				client.start();
			}
			else if (command.equals("user2uploader"))
			{
				TestUserToUploader test = new TestUserToUploader();
				test.testIt();
			}
			else if (command.equals("compiler2uploader"))
			{
				TestCompilerToUploader test = new TestCompilerToUploader();
				test.testIt();
			}
			else if (command.equals("uploader2user"))
			{
				TestUploaderToUser test = new TestUploaderToUser();
				test.testIt();
			}
			else if (command.startsWith("x"))
			{
				
				System.out.print("Disconnecting from database...");
				HibernateUtils.shutdown();
				System.out.println(" done");
				System.out.print("Shutting down sockets...");
				webSocketServer.shutdown();
				
				if(webSocketServer != null){
					try {
						webSocketServer.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(" done");
				System.out.println("FileUploader terminated.");
				break;				
			}
		}
		System.out.println("Bye");
	}

}
