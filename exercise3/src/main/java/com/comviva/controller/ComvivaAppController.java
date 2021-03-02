package com.comviva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.comviva.services.NumericProcessorService;

@Controller
public class ComvivaAppController {
	
	@Autowired
	private NumericProcessorService numericProcessorService;
	
	@GetMapping("/")
    public String index() {
        return "index";
    }	
	
	@GetMapping("/upload-file")
    public String uploadFile() {
        return "upload-file";
    }	
	
	@GetMapping("/view-table")
    public String viewTable(Model model) {
		model.addAttribute("files", numericProcessorService.getFileTableContent());
        return "view-table";
    }	
	
	
	@PostMapping("/process-file")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttribute) {
		try {
			if (file == null) {
				redirectAttribute.addFlashAttribute("message", "Por favor, seleccione un archivo");
			} else {
				if (file.getContentType().equals("text/plain")) {
					numericProcessorService.processNumericContent(file);
					redirectAttribute.addFlashAttribute("message", "Archivo subido correctamente!");
				} else {
					redirectAttribute.addFlashAttribute("message", "El archivo debe ser texto plano (.txt) ");
				}
			}
			return "redirect:/upload-file";
		} catch(Exception e) {
			redirectAttribute.addFlashAttribute("message", "Error al subir el archivo");
			return "redirect:/upload-file";
		}
	}
}
