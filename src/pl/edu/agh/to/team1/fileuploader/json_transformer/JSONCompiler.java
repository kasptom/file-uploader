package pl.edu.agh.to.team1.fileuploader.json_transformer;




public class JSONCompiler extends JSONTransformer{

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
