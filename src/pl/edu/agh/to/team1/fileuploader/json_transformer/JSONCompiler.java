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
