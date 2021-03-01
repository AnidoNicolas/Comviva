package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExerciseOne {

	public static void main(String[] args) {

		System.out.println("Ingrese la ruta del archivo a procesar");
		String fileContent = getFileContent(readInput());
		String fileNumericContent [] = filterNumericContent(fileContent);
		String resultText = buildResultText(fileNumericContent);
		System.out.println(resultText);
		
	}
	
	private static String readInput() {
		String inputValue = null;
		try (BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in))){
			inputValue = reader.readLine();
		} catch (IOException e) {
			System.out.printf("Hubo un error al intentar leer el valor ingresado. Error: %s", e.getMessage());
		}
		return inputValue;
	}
	
	private static String getFileContent(String path) {
		String fileContent = null;
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
			fileContent = new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.printf("Hubo un error durante la decodificación del archivo. Error: %s", e.getMessage());
		}
		return fileContent;
	}

	private static String[] filterNumericContent (String fileContent) {
		String filteredFileContent = fileContent.replaceAll("[^\\d.]"," ").replaceAll("\\s{2,}", " ").trim();
		return (!filteredFileContent.equals(""))? filteredFileContent.split(" "): null;
	}
	
	private static String buildResultText (String [] fileNumericContent) {
		if (fileNumericContent!= null) {
			StringBuilder resultText = new StringBuilder();
			Float total = Float.valueOf(0);
			for (int i = 0; i < fileNumericContent.length ; i++) {
				resultText.append(fileNumericContent[i]);
				total = total + Float.valueOf(fileNumericContent[i]);
				if(fileNumericContent.length - 1 != i) {
					resultText.append(" + ");
				} else {
					resultText.append(" = ");
					resultText.append(total.toString());
				}
			}
			return resultText.toString();
		}
		return "No había números en el archivo, por lo que no se realizó ninguna acción.";
	}
}
