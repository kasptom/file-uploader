package pl.edu.agh.to.team1.fileuploader.server;

import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import pl.edu.agh.to.team1.fileuploader.filemanager.FileManager;

@ServerEndpoint(value = "/compiler")
public class ServerEndpointCompiler {
	FileManager fileManager = new FileManager();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("COMPILER ENDPOINT: Web Socket server opened.");
	}
	
	/*@OnMessage
    public String onMessage(String message, Session session) {
		System.out.println("SERVER: received message: "+ message +", from: "+session.getId());
        return message;
    }*/
	
	@OnMessage
	public void handleMessage(InputStream stream, Session session) {
		System.out.println("COMPILER ENDPOINT: received message: "+ stream.toString());
		fileManager.handleFile(stream, "compiler.json");
	}
	@OnError
	public void onError(Throwable t){
		t.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session){
		System.out.println("Session: "+ session.getId() + " closed.");
	}
}