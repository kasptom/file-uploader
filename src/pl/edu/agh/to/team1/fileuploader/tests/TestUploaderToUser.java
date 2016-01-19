package pl.edu.agh.to.team1.fileuploader.tests;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import net.sf.json.JSONObject;
import pl.edu.agh.to.team1.fileuploader.main.ConfigManager;
import pl.edu.agh.to.team1.fileuploader.server.MyClient;

public class TestUploaderToUser {
	private MyClient userAndStatsClient = new MyClient();
	private ConfigManager confManager = new ConfigManager(); 
	private String userAndStatsWebSocketAddress = "ws://<host-address-of-user-and-stats>:<port-on-user-and-stats>/<...>";
	private InputStream jsonStream = null;
	private JSONObject jsonToUserAndStats = null;
	
	
	public void testIt(){
		jsonToUserAndStats = createJSONToUserAndStats();
		jsonStream = new ByteArrayInputStream(jsonToUserAndStats.toString().getBytes());
		userAndStatsWebSocketAddress = confManager.getValue("USR_WSC");
		userAndStatsClient.sendJSON(jsonStream, userAndStatsWebSocketAddress);								//uncomment if compiler is ready
	}
	
	private JSONObject createJSONToUserAndStats(){
		 long userId = 1;
	     long solutionId = 1;
	     long taskNumber = 1;
	     String resultType = "OK";
	     double resultValue = 0.01;
		
		JSONObject obj = new JSONObject();
	    obj.put("user_id", userId);
		obj.put("solution_id", solutionId);
		obj.put("task_number", taskNumber);
		obj.put("result_type", resultType);
		obj.put("result_value", resultValue);
		return obj;
	}
}
