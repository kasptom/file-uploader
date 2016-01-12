package pl.edu.agh.to.team1.fileuploader.server;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class TestClient extends Thread {
    private CountDownLatch messageLatch;
    private static final String SENT_MESSAGE = "Hello World";
    ByteBuffer stream = ByteBuffer.wrap(SENT_MESSAGE.getBytes(StandardCharsets.UTF_8));

    public void run(){
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
                        //session.getBasicRemote().sendText(SENT_MESSAGE);
                        session.getBasicRemote().sendBinary(stream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI("ws://localhost:8025/file-uploader/compiler"));
            //messageLatch.await(100, TimeUnit.SECONDS);
            
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
                        //session.getBasicRemote().sendText(SENT_MESSAGE);
                        session.getBasicRemote().sendBinary(stream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI("ws://localhost:8026/file-uploader/user-and-stats"));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}