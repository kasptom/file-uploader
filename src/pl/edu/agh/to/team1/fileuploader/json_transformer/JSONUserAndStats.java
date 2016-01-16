package pl.edu.agh.to.team1.fileuploader.json_transformer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import pl.edu.agh.to.team1.fileuploader.filemanager.FileManager;
import pl.edu.agh.to.team1.fileuploader.server.CompilerClient;


public class JSONUserAndStats extends JSONTransformer {
	private FileManager fileManager = new FileManager();
	private CompilerClient compilerClient = new CompilerClient();
	
	
	@SuppressWarnings("unused")
	public void handleStream(InputStream inputStream){
		//create JSONObject from stream
		
		try {
			String jsonTxt = IOUtils.toString(inputStream);
			JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
			//extract data from compiler
			long userId = json.getLong("user_id");
			long solutionId = json.getLong("solution_id");
			byte[] fileBytes = Base64.getDecoder().decode((String)json.get("file"));
			InputStream fileStream = new ByteArrayInputStream(fileBytes);
			long taskNumber = 1;	//simplified
			String inputString = json.getString("input"); 
			String outputString = json.getString("output");
			double timeout = json.getDouble("timeout");
			//process data
			//save result in database - TODO
			
			//save file on the disk
			String fileName = String.valueOf(userId) + solutionId; 
			fileManager.handleFile(fileStream, fileName);
			
			//send File to compiler
			JSONObject jsonToCompiler = this.createJSONToCompiler(solutionId, (Base64)json.get("file"), inputString, outputString, timeout);
			InputStream jsonStream = new ByteArrayInputStream(jsonToCompiler.toString().getBytes());
			
			compilerClient.sendJSON(jsonStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private JSONObject createJSONFromCompiler(long userID, String solutionID, long taskNumber,String resultType, double resultValue){
		JSONObject obj = new JSONObject();
		obj.put("user_id", userID);
		obj.put("solution_id", solutionID);
		obj.put("task_number", taskNumber);
		obj.put("result_type", resultType);
		obj.put("result_value", resultValue);
		return obj;
	}
	
	private JSONObject createJSONToCompiler(long solutionId, Base64 fileBase64, String input, String output, double timeout){
		JSONObject obj = new JSONObject();
		obj.put("solution_id", solutionId);
		obj.put("file", fileBase64);
		obj.put("input", input);
		obj.put("output", output);
		obj.put("timeout", timeout);
		return obj;
	}
}
