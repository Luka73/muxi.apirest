package com.muxi.apirest.controller;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muxi.apirest.model.Terminal;
import com.muxi.apirest.repository.TerminalRepository;
import com.muxi.apirest.utils.JsonUtils;


@RestController
@RequestMapping(value="/api")
public class TerminalController {
	@Autowired
	TerminalRepository terminalRepository;
	
	@PostMapping("/terminal")
	public void postTerminal(@RequestBody String terminal) throws IOException{
		String[] splitted = terminal.split(";");
		JsonUtils jsonUtils = new JsonUtils();
		JSONObject json = jsonUtils.stringToJson(splitted);
	
		
		try (InputStream inputStream = new ClassPathResource("schema.json").getInputStream()) {
		  JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
		  Schema schema = SchemaLoader.load(rawSchema);
		  schema.validate(new JSONObject(json));
		  
		  //terminalRepository.save();
		  
		  //System.out.println("Funcionou!");
		}
		catch(NumberFormatException e){
			throw new NumberFormatException ("Formato inválido");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	
	@GetMapping("/terminal/{id}")
	public Terminal listaProdutoUnico(@PathVariable(value="id") int logic) {
		return terminalRepository.findById(logic);
	}
	
}
