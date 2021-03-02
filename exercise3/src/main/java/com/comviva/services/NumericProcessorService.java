package com.comviva.services;


import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.comviva.entities.ResultOutput;
import com.comviva.repository.IResultOutputRepository;
import com.mysql.cj.util.StringUtils;

@Service
public class NumericProcessorService {

	private static final Logger logger = LogManager.getLogger(NumericProcessorService.class);

	@Autowired
	private IResultOutputRepository resultOutputRepository;
	
	public void processNumericContent(MultipartFile file) {
		try {
			String fileContent = getFileContent(file.getBytes());
			String fileNumericContent [] = filterNumericContent(fileContent);
			String resultText = buildResultText(fileNumericContent);
			if (!StringUtils.isNullOrEmpty(resultText)) {
				saveResultText(file.getOriginalFilename(), resultText);
			}
		} catch (Exception e) {
			logger.error("No se pudo realizar la operación.\n");
		}
		
	}
	
	private static String getFileContent(byte[] encoded) throws Exception{
		String fileContent = null;
		try {
			fileContent = new String(encoded, StandardCharsets.UTF_8);
		} catch (Exception e) {
			logger.error("Hubo un error durante la decodificación del archivo.\n");
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
		logger.info("No había números en el archivo, por lo que no se realizó ninguna acción.");
		return null;
	}
	
	@Transactional
	private void saveResultText(String fileName, String fileContent) {
		try {
			ResultOutput resultOutput = new ResultOutput(fileName, fileContent, new Date());
			logger.info("Se procede a guardar resultado: %s %n", fileContent);
			resultOutputRepository.save(resultOutput);
			logger.info("Datos almacenados correctamente!\n");
		} catch (Exception e) {
			logger.error("Se produjo un error al guardar el registro.\n");
		}
		
	}
	
	@Transactional
	public List<ResultOutput> getFileTableContent() {
		return resultOutputRepository.findAll();
	}
}
