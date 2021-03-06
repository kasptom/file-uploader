package pl.edu.agh.to.team1.fileuploader.server;

import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import pl.edu.agh.to.team1.fileuploader.filemanager.FileManager;
import pl.edu.agh.to.team1.fileuploader.json_transformer.JSONUserAndStats;

@ServerEndpoint(value = "/user-and-stats")
public class ServerEndpointUserAndStats {
	FileManager fileManager = new FileManager();
	JSONUserAndStats jsonUserAndStats = new JSONUserAndStats();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("USER AND STATS ENDPOINT: Web Socket server opened.");
	}
	
	/*@OnMessage
    public String onMessage(String message, Session session) {
		System.out.println("SERVER: received message: "+ message +", from: "+session.getId());
        return message;
    }*/
	
	/*@OnMessage
	public void handleMessage(Object stream, Session session) {
		if(stream instanceof String){
			System.out.println("USER AND STATS ENDPOINT: received message: "+ stream);
		}else if(stream instanceof InputStream){
			System.out.println("USER AND STATS ENDPOINT: received input stream");
			fileManager.handleFile((InputStream)stream, "user-test-file.json");
		}
	}*/
	@OnMessage
	public void handleMessage(InputStream stream, Session session) {
		
		System.out.println("USER AND STATS ENDPOINT: received input stream: " + stream.toString());
		jsonUserAndStats.handleStream(stream);	// 
		//fileManager.handleFile(stream, "user-test-file.json");
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
