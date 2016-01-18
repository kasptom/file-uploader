package pl.edu.agh.to.team1.fileuploader.tests;

import javax.websocket.*;

import java.io.IOException;

@ClientEndpoint
public class WebSocketClient {
	@SuppressWarnings("unused")
	private final String uri="ws://localhost:8080";
		   private Session session;

		   @OnOpen
		   public void onOpen(Session session){
		      this.session=session;
		      System.out.println("Session: " + session.getId() + " opened.");
		   }

		   @OnMessage
		   public void onMessage(String message, Session session){
		      System.out.println("Client received: " +message);
		   }

		   public void sendMessage(String message){
		      try {
		         session.getBasicRemote().sendText(message);
		      } catch (IOException ex) {
		    	  ex.printStackTrace();
		      }
		   }
}
