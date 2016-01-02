package pl.edu.agh.to.team1.fileuploader.server;
import org.glassfish.tyrus.server.Server;

public class WebSocketServer extends Thread{
	private Server server;
	private volatile boolean done = false;
	
	public void run(){
		server = new Server("localhost", 8025, "/file-uploader", ServerEndpointAnnotated.class);
        try {
            server.start();
            while(!done){}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	System.out.println("\nSERVER: stopping server.");
            server.stop();
        }
	}
	
	public void shutdown(){
		this.done = true;
	}
}
