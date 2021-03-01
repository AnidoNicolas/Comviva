package com.comviva.exercise2.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comviva.exercise2.entities.ResultOutput;
import com.comviva.exercise2.repository.IResultOutputRepository;
import com.mysql.cj.util.StringUtils;

@Service
public class NumericProcessorService {

	@Autowired
	private IResultOutputRepository resultOutputRepository;
	
	public void processNumericContent(String path) {
		try {
			String fileContent = getFileContent(path);
			String fileNumericContent [] = filterNumericContent(fileContent);
			String resultText = buildResultText(fileNumericContent);
			if (!StringUtils.isNullOrEmpty(resultText)) {
				saveResultText(getFileName(path), resultText);
			}
		} catch (Exception e) {
			System.out.printf("No se pudo realizar la operación.\n");
		}
		
	}
	
	private static String getFileContent(String path) throws Exception{
		String fileContent = null;
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
			fileContent = new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.printf("Hubo un error durante la decodificación del archivo.\n");
			throw e;
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
		System.out.println("No había números en el archivo, por lo que no se realizó ninguna acción.");
		return null;
	}
	
	private static String getFileName(String path) {
		return Paths.get(path).getFileName().toString();
	}
	
	@Transactional
	private void saveResultText(String fileName, String fileContent) {
		try {
			ResultOutput resultOutput = new ResultOutput(fileName, fileContent, new Date());
			System.out.printf("Se procede a guardar resultado: %s \n", fileContent);
			resultOutputRepository.save(resultOutput);
			System.out.println("Datos almacenados correctamente!\n");
		} catch (Exception e) {
			System.out.printf("Se produjo un error al guardar el registro.\n");
		}
		
	}
}
