package com.comviva.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.comviva.exercise2.repository.IResultOutputRepository;
import com.comviva.exercise2.services.NumericProcessorService;

@SpringBootApplication
@EnableJpaRepositories
public class ExerciseTwo implements CommandLineRunner{

	@Autowired
	public IResultOutputRepository resultOutputRepository;
	
	@Autowired
	public NumericProcessorService numericProcessorService;
	
	public static void main(String[] args) {
	  System.out.println("Se inicia aplicación, aguarde...");
      SpringApplication.run(ExerciseTwo.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ingrese la ruta del archivo a procesar: ");
		String path = readInput();
		numericProcessorService.processNumericContent(path);
	}
	
	private static String readInput() {
		String inputValue = null;
		try (BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in))){
			inputValue = reader.readLine();
		} catch (IOException e) {
			System.out.printf("Hubo un error al intentar leer el valor ingresado.");
		}
		return inputValue;
	}
	
	

	

}