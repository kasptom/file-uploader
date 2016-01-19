package pl.edu.agh.to.team1.fileuploader.json_transformer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import pl.edu.agh.to.team1.fileuploader.db.DBManager;
import pl.edu.agh.to.team1.fileuploader.main.ConfigManager;
import pl.edu.agh.to.team1.fileuploader.server.MyClient;

  

public class JSONCompiler extends JSONTransformer{
	private DBManager dbManager = new DBManager();
	ConfigManager confManager = new ConfigManager();
	private MyClient userAndStatsClient = new MyClient();
	private String userAndStatsWebSocketAddress = "ws://<host-address-of-user-and-stats>:<port-on-user-and-stats>/<...>"; 
	
	public void handleStream(InputStream inputStream){
		//create JSONObject from stream
		
		try {
			String jsonTxt = IOUtils.toString(inputStream);
			JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
			//extract data from compiler
			long solutionId = json.getLong("solution_id");
			String resultType = json.getString("result_type");
			double resultValue = json.getDouble("result_value");
			//process data
			//save result in database
			
			
			//
			long userId = 1;
			long taskNumber = 1;
			//send data to UserAndStats
			JSONObject jsonToUserAndStats = createJSONToUserAndStats(userId, solutionId, taskNumber, resultType, resultValue);
			new ByteArrayInputStream(jsonToUserAndStats.toString().getBytes());
			System.out.println("sedning data from Compiler to User and Stats");
			userAndStatsWebSocketAddress = confManager.getValue("USR_WSC");
			System.out.println(userAndStatsWebSocketAddress);
			//userAndStatsClient.sendJSON(inputStream, userAndStatsWebSocketAddress); //uncomment if ready	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JSONObject createJSONToUserAndStats(long userId, long solutionId, long taskNumber, String resultType, double resultValue){
		JSONObject obj = new JSONObject();
	    obj.put("user_id", userId);
		obj.put("solution_id", solutionId);
		obj.put("task_number", taskNumber);
		obj.put("result_type", resultType);
		obj.put("result_value", resultValue);
		return obj;
	}
}



/*
	//za Piotrkiem
OutCompiler:
{
	solutionId: id rozwi¹zania (w postaci long(?))
	file: reprezentacja pliku/paczki w postaci base64
	testValues:{
		test1:{ <----- dowolna wartoœæ, niekoniecznie test1/test2 itp.
			input:"Input values 1"
			output:"Output values 1"
		}
		timeout: czas po którym powinien nast¹piæ timeout
	}
	
}

InCompiler:
{
	solution_id: id rozwi¹zania dla którego przeprowadzony zosta³ test
	result_type: jedna z wartoœci OK, TIMEOUT, RUNTIME_ERROR, COMPILATION_ERROR, RESOURCE_ERROR // (do sprawdzenia, mo¿liwe, ¿e w takim przypadku program zwróci runtime error (jako wyj¹tek w trakcie wykonania podczas próby zaw³aszczenia))
							 WRONG_RESULT
	result_value: --- w przypadku OK liczbowa wartoœæ reprezentuj¹ca czas wykonania, w przeciwnym wypadku tego pola nie bêdzie.				 
}

*/
