package pl.edu.agh.to.team1.fileuploader.json_transformer;




public class JSONCompiler extends JSONTransformer{

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
