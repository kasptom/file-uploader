package pl.edu.agh.to.team1.fileuploader.trash;

import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
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
		System.out.println("SERVER: received message: "+ message +", from: "+session.getId());
        return message;
    }*/
	
	@OnMessage
	public void handleMessage(Object stream, Session session) {
		if(stream instanceof InputStream){
			System.out.println("SERVER: received input stream");
			fileManager.handleFile((InputStream)stream, "test.json");
		}else if(stream instanceof String){
			System.out.println("SERVER: received text message: "+ stream);
		}
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
