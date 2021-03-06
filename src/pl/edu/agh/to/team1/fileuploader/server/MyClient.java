package pl.edu.agh.to.team1.fileuploader.server;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.apache.commons.io.IOUtils;
import org.glassfish.tyrus.client.ClientManager;

public class MyClient{
    private CountDownLatch messageLatch;

    public void sendJSON(InputStream stream, String websocketAddress){
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
                        session.getBasicRemote().sendBinary(ByteBuffer.wrap((IOUtils.toByteArray(stream))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI(websocketAddress));
         
            messageLatch.await(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}