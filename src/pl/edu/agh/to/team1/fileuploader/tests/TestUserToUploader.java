package pl.edu.agh.to.team1.fileuploader.tests;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.apache.commons.io.IOUtils;
import org.glassfish.tyrus.client.ClientManager;

import net.sf.json.JSONObject;

public class TestUserToUploader {
	private CountDownLatch messageLatch;
	private InputStream jsonStream = null;
	private JSONObject jsonToFileUploader = null;
	
	public void testIt(){
		try {
            messageLatch = new CountDownLatch(1);
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
            ClientManager client = ClientManager.createClient();
            client.connectToServer(new Endpoint() {

                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    try {
                        session.addMessageHandler(new MessageHandler.Whole<String>() {
                            @Override
                            public void onMessage(String message) {
                                System.out.println("Received message: "+message);
                                messageLatch.countDown();
                            }
                        });
                        jsonToFileUploader = prepareJSONForFileUploader();
                        jsonStream = new ByteArrayInputStream(jsonToFileUploader.toString().getBytes());
                		session.getBasicRemote().sendBinary(ByteBuffer.wrap((IOUtils.toByteArray(jsonStream))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI("ws://localhost:8025/file-uploader/user-and-stats"));           
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private JSONObject prepareJSONForFileUploader(){ 
		InputStream fileStream;
		JSONObject obj = new JSONObject();
		try {
			fileStream = new FileInputStream("TMP/HelloWorld.c");
			byte arrayToEncode[];
			arrayToEncode = IOUtils.toByteArray(fileStream);
		
			String base64FileString =  new String(Base64.getEncoder().encode(arrayToEncode));
		
			obj.put("user_id", (long)1);
			obj.put("task_number", (long)1 );
			obj.put("file",base64FileString);
			obj.put("input", "");
			obj.put("output", "Hello World!");
			obj.put("timeout", 1000.0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
