package com.comviva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comviva.entities.ResultOutput;
import com.comviva.services.NumericProcessorService;

@RestController
public class RestAPIController {
	
	@Autowired
	private NumericProcessorService numericProcessorService;
	
	@GetMapping(value = "/get-files-list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ResultOutput> consultaRegistro() {
		return numericProcessorService.getFileTableContent();
	}	
}
