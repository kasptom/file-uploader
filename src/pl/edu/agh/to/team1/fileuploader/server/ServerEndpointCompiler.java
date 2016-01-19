package pl.edu.agh.to.team1.fileuploader.server;

import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import pl.edu.agh.to.team1.fileuploader.filemanager.FileManager;
import pl.edu.agh.to.team1.fileuploader.json_transformer.JSONCompiler;

@ServerEndpoint(value = "/compiler")
public class ServerEndpointCompiler {
	FileManager fileManager = new FileManager();
	JSONCompiler jsonCompiler = new JSONCompiler();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("COMPILER ENDPOINT: Web Socket server opened.");
	}
	
	/*@OnMessage
    public String onMessage(String message, Session session) {
		System.out.println("SERVER: received message: "+ message +", from: "+session.getId());
        return message;
    }*/
	
	/*@OnMessage
	public void handleMessage(Object stream, Session session) {
		//jsonCompiler.handleStream(stream);	//uncomment 
		if(stream instanceof String){
			System.out.println("COMPILER ENDPOINT: received message: "+ stream);
		}else if(stream instanceof InputStream){
			System.out.println("COMPILER ENDPOINT: received input stream");
			fileManager.handleFile((InputStream)stream, "compiler-test-file.json");
		}
	}*/
	@OnMessage
	public void handleMessage(InputStream stream, Session session) {
		System.out.println("COMPILER ENDPOINT: received input stream: " + stream.toString());    
		jsonCompiler.handleStream(stream);	//uncomment 
		//fileManager.handleFile(stream, "compiler-test-file.json");
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
