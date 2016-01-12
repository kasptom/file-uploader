package pl.edu.agh.to.team1.fileuploader.server;
import org.glassfish.tyrus.server.Server;

public class WebSocketServer extends Thread{
	private Server serverUserAndStats;
	private Server serverCompiler;
	private volatile boolean done = false;
	
	public void run(){
		serverUserAndStats = new Server("localhost", 8025, "/file-uploader", ServerEndpointUserAndStats.class);
		serverCompiler = new Server("localhost", 8026, "/file-uploader", ServerEndpointCompiler.class);
        try {
            serverUserAndStats.start();
            serverCompiler.start();
            while(!done){}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	System.out.println("\nSERVER: stopping server.");
            serverUserAndStats.stop();
            serverCompiler.stop();
        }
	}
	
	public void shutdown(){
		this.done = true;
	}
}
