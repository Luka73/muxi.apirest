package com.muxi.apirest.controller;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muxi.apirest.model.Terminal;
import com.muxi.apirest.repository.TerminalRepository;
import com.muxi.apirest.utils.JsonUtils;
import com.muxi.apirest.utils.SchemaValidation;


@RestController
@RequestMapping(value="/v1")
public class TerminalController {
	@Autowired
	TerminalRepository terminalRepository;
	
	@PostMapping(path="/terminal", consumes=MediaType.TEXT_HTML_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void postTerminal(@Validated() @RequestBody String terminal) throws IOException{
		String[] splitted = terminal.split(";");
		JsonUtils jsonUtils = new JsonUtils();
		SchemaValidation vJson = new SchemaValidation();
		JSONObject json = jsonUtils.stringToJson(splitted);
		boolean isValid = vJson.validaJson(json);
		
		if(isValid)
		{
			String sJson = json.toString();
			System.out.println(sJson);
			ObjectMapper mapper = new ObjectMapper();
			Terminal term = mapper.readValue(sJson, Terminal.class);
			terminalRepository.save(term);
		}              
	}
	
	@GetMapping(path="/terminal/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Terminal listaProdutoUnico(@PathVariable(value="id") int logic) {
		return terminalRepository.findById(logic);
	}
	
}
