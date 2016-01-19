package pl.edu.agh.to.team1.fileuploader.tests;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.apache.commons.io.IOUtils;
import org.glassfish.tyrus.client.ClientManager;

import net.sf.json.JSONObject;

public class TestCompilerToUploader {
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
            }, cec, new URI("ws://localhost:8026/file-uploader/compiler"));           
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	private JSONObject prepareJSONForFileUploader(){
		long userID = 1;
		String solutionID = "1_1_1";
		long taskNumber = 1;
		String resultType = "OK";
		double resultValue = 0.01;
		
		JSONObject obj = new JSONObject();
		obj.put("user_id", userID);
		obj.put("solution_id", solutionID);
		obj.put("task_number", taskNumber);
		obj.put("result_type", resultType);
		obj.put("result_value", resultValue);
		return obj;
	}
}
