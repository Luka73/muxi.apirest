package com.muxi.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muxi.apirest.service.ConstantPaths;
import com.muxi.apirest.model.Terminal;
import com.muxi.apirest.repository.TerminalRepository;
import com.muxi.apirest.service.JsonUtils;
import com.muxi.apirest.service.SchemaValidation;


@RestController
@RequestMapping(value=ConstantPaths.API.V1.URL_API_VERSION)
public class TerminalController {
	
	@Autowired
	private TerminalRepository terminalRepository;
	@Autowired
	private JsonUtils jsonUtils;
	@Autowired
	private SchemaValidation vJson;
	
	@Autowired
	private Validator validator;
	
	@PostMapping(path=ConstantPaths.API.URL_POST, consumes=MediaType.TEXT_HTML_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@RequestBody String terminal, BindingResult result){
		
		try 
		{
			JSONObject json = jsonUtils.stringToJson(terminal.split(";"));
			ObjectMapper mapper = new ObjectMapper();
			Terminal term = mapper.readValue(json.toString(), Terminal.class);
	        Set<ConstraintViolation<Terminal>> violations = validator.validate(term);
	        
	        if (violations.size() == 0) 
	        {	        	
	        	if(vJson.validaJson(json))
				{
					terminalRepository.save(term);
					return ResponseEntity.ok(term);
				} 
	        	
	        } else {	  
	        	
	        	List<String> errors = new ArrayList<String>();

	        	for (ConstraintViolation<Terminal> v : violations) {
	        		 errors.add(v.getMessage());
	        	}
	        	
	        	return ResponseEntity.badRequest().body(errors);  
	        }
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body("Formato Inválido");
		}
	
		return ResponseEntity.badRequest().body(result);
	}
	
	@GetMapping(path=ConstantPaths.API.URL_GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Terminal listaProdutoUnico(@PathVariable(value="id") Integer logic) {
		return terminalRepository.findByLogic(logic);
	}
	
	
}
