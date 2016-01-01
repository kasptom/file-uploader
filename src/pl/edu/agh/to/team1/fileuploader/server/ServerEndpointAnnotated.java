package pl.edu.agh.to.team1.fileuploader.server;

import java.io.InputStream;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import pl.edu.agh.to.team1.fileuploader.filemanager.FileManager;

@ServerEndpoint(value = "/file-server")
public class ServerEndpointAnnotated {
	FileManager fileManager = new FileManager();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("SERVER: Web Socket server opened.");
	}
	
	/*@OnMessage
    public String onMessage(String message, Session session) {
        //return message;
    }*/
	
	@OnMessage
	public void handleMessage(InputStream stream, Session session) {
		System.out.println("SERVER: received message: "+ stream.toString());
		fileManager.handleFile(stream, "test_file.json");
	}
}