package pl.edu.agh.to.team1.fileuploader.server;

/*import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import pl.edu.agh.to.team1.fileuploader.json_transformer.*;*/

public class SocketListener extends Thread{
	
	/*private volatile boolean done = false;
	private final String hostName;
	private int userStatPort = 2001;
	private int compilerPort = 2003;
	private JSONTransformer compilerJSON = new JSONCompiler();
	private JSONTransformer userStatJSON = new JSONUserAndStats();
	
	public SocketListener(String hostName){
		this.hostName = hostName;
	}
	
	
	public void listen(){
		try{
			Socket statAndUserSocket = new Socket(hostName, userStatPort);
			Socket compilerSocket = new Socket(hostName, compilerPort);
					
			OutputStream outUserStat = null; 
			OutputStream outCompiler = null;  
			
			while(!done){
				outUserStat = statAndUserSocket.getOutputStream();
				outCompiler = compilerSocket.getOutputStream();
				userStatJSON.process(outUserStat);
				compilerJSON.process(outCompiler);
			}
		}catch(IOException e){
			
		}finally{
			
		}
		return;

	}
	
	public void run(){
		
		this.listen();
	}
	public void shutdown(){
		this.done = true;
	}*/
}
