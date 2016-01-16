package pl.edu.agh.to.team1.fileuploader.json_transformer;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import pl.edu.agh.to.team1.fileuploader.db.DBManager;

  

public class JSONCompiler extends JSONTransformer{
	@SuppressWarnings("unused")
	private JSONUserAndStats jsonUserAndStats = new JSONUserAndStats();
	@SuppressWarnings("unused")
	private DBManager dbManager = new DBManager();
	
	@SuppressWarnings("unused")
	public void handleStream(InputStream inputStream){
		//create JSONObject from stream
		
		try {
			String jsonTxt = IOUtils.toString(inputStream);
			JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
			//extract data from compiler
			String solutionId = json.getString("solution_id");
			String resultType = json.getString("result_type");
			double resultValue = json.getDouble("result_value");
			//process data
			//save result in database
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/*
	//za Piotrkiem
OutCompiler:
{
	solutionId: id rozwi�zania (w postaci long(?))
	file: reprezentacja pliku/paczki w postaci base64
	testValues:{
		test1:{ <----- dowolna warto��, niekoniecznie test1/test2 itp.
			input:"Input values 1"
			output:"Output values 1"
		}
		timeout: czas po kt�rym powinien nast�pi� timeout
	}
	
}

InCompiler:
{
	solution_id: id rozwi�zania dla kt�rego przeprowadzony zosta� test
	result_type: jedna z warto�ci OK, TIMEOUT, RUNTIME_ERROR, COMPILATION_ERROR, RESOURCE_ERROR // (do sprawdzenia, mo�liwe, �e w takim przypadku program zwr�ci runtime error (jako wyj�tek w trakcie wykonania podczas pr�by zaw�aszczenia))
							 WRONG_RESULT
	result_value: --- w przypadku OK liczbowa warto�� reprezentuj�ca czas wykonania, w przeciwnym wypadku tego pola nie b�dzie.				 
}

*/
