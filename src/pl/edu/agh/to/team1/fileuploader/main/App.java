package pl.edu.agh.to.team1.fileuploader.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pl.edu.agh.to.team1.fileuploader.server.TestClient;
import pl.edu.agh.to.team1.fileuploader.server.WebSocketServer;
import pl.edu.agh.to.team1.fileuploader.persistence.HibernateUtils;

public class App{
	public static void main(String[] args) throws IOException {
		HibernateUtils.getSession().close();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		WebSocketServer webSocketServer = new WebSocketServer();
		webSocketServer.start();
		TestClient client = new TestClient();
		client.start();
		
		while(true){
			System.out.println("FileUploader server [? for help] > :");
			String command = bufferedReader.readLine();
			if (command.startsWith("?"))
			{
				System.out.println("'?'      	- print this help");
				System.out.println("'x'      	- exit FileUploader");
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
