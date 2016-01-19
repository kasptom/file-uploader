package pl.edu.agh.to.team1.fileuploader.json_transformer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import pl.edu.agh.to.team1.fileuploader.db.DBManager;
import pl.edu.agh.to.team1.fileuploader.filemanager.FileManager;
import pl.edu.agh.to.team1.fileuploader.main.ConfigManager;
import pl.edu.agh.to.team1.fileuploader.server.MyClient;


public class JSONUserAndStats extends JSONTransformer {
	private FileManager fileManager = new FileManager();
	private MyClient compilerClient = new MyClient();
	private String compilerWebSocketAddress = "ws://<host-address-of-compiler>:<port-on-compiler>/<...>";
	private DBManager manager = new DBManager();
	
	@SuppressWarnings("unused")
	public void handleStream(InputStream inputStream){
		//create JSONObject from stream
		
		try {
			ConfigManager confManager = new ConfigManager();
			String jsonTxt = IOUtils.toString(inputStream);
			JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
			//extract data from compiler
			long userId = json.getLong("user_id");
			//long solutionId = json.getLong("solution_id"); <--- deleted
			long taskNumber = json.getLong("task_number");
			byte[] fileBytes = Base64.getDecoder().decode((String)json.get("file"));
			InputStream fileStream = new ByteArrayInputStream(fileBytes);
			String inputString = json.getString("input"); 
			String outputString = json.getString("output");
			double timeout = json.getDouble("timeout");
			//process data
			//save result in database - TODO
			
			//save file on the disk
			long solutionId = 1; //simplified
			String fileName = String.valueOf(userId) +"_" +String.valueOf(taskNumber)+"_" +String.valueOf(solutionId); 
			fileManager.handleFile(fileStream, fileName);
			
			//send File to compiler 
			compilerWebSocketAddress = confManager.getValue("COM_WSC");
			System.out.println(compilerWebSocketAddress);
			JSONObject jsonToCompiler = this.createJSONToCompiler(solutionId, (String)json.get("file"), inputString, outputString, timeout);
			InputStream jsonStream = new ByteArrayInputStream(jsonToCompiler.toString().getBytes());
			//compilerClient.sendJSON(jsonStream, compilerWebSocketAddress);								//uncomment if compiler is ready
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private JSONObject createJSONToCompiler(long solutionId, String fileBase64, String input, String output, double timeout){
		JSONObject obj = new JSONObject();
		obj.put("solution_id", solutionId);
		obj.put("file", fileBase64);
		obj.put("input", input);
		obj.put("output", output);
		obj.put("timeout", timeout);
		return obj;
	}
}
