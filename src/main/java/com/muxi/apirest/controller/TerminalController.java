package com.muxi.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muxi.apirest.service.ConstantPaths;
import com.muxi.apirest.dto.Response;
import com.muxi.apirest.model.Terminal;
import com.muxi.apirest.repository.TerminalRepository;
import com.muxi.apirest.response.VerifyViolations;
import com.muxi.apirest.service.JsonUtils;
import com.muxi.apirest.service.SchemaValidation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping(value=ConstantPaths.API.V1.URL_API_VERSION)
public class TerminalController {
	
	@Autowired
	private TerminalRepository terminalRepository;
	@Autowired
	private JsonUtils jsonUtils;
	@Autowired
	private SchemaValidation vJson;
	
	Response response = new Response(); 
	VerifyViolations valid = new VerifyViolations();

	@RequestMapping(value = "**", method = RequestMethod.POST)
    public ResponseEntity<?> postUnauthorizedEndpoint() {
        return ResponseEntity.badRequest().body(response);
    }

	@PostMapping(path=ConstantPaths.API.URL_POST, consumes=MediaType.TEXT_HTML_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create terminal", notes = "Creating a new terminal", response = Terminal.class)
	public ResponseEntity<?> postTerminal(@RequestBody String terminal, BindingResult result){

		try 
		{
			JSONObject json = jsonUtils.stringToJson(terminal.split(";"));
			
			if(!vJson.validaJson(json))
				return ResponseEntity.badRequest().body(response);
				
			ObjectMapper mapper = new ObjectMapper();
			Terminal term = mapper.readValue(json.toString(), Terminal.class);
	        
			if(valid.verifyViolations(term) == null) {
				terminalRepository.save(term);
				return ResponseEntity.ok(term);
			} else {
				return ResponseEntity.badRequest().body(valid.verifyViolations(term));  
			}
	        
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping(path=ConstantPaths.API.URL_GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Terminal getTerminalByLogic(@ApiParam("The unique identifier of the terminal") @PathVariable(value="id") Integer logic) {
		return terminalRepository.findByLogic(logic);
	}
	
	
    @ApiOperation(value = "Update terminal", notes = "Updating an existing terminal", response = Terminal.class)
	@PutMapping(path=ConstantPaths.API.URL_PUT, consumes=MediaType.TEXT_HTML_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> putTerminalByLogic(@PathVariable(value="id") Integer logic, @RequestBody String terminal) {
    	
    	try 
		{
			JSONObject json = jsonUtils.stringToJson(terminal.split(";"));
			Terminal term = terminalRepository.findByLogic(Integer.parseInt(json.getString("logic")));
			
			 if (term == null || !vJson.validaJson(json)) {
	             return ResponseEntity.badRequest().body(response);
	         }
			 
			if(valid.verifyViolations(term) == null) {
				terminalRepository.save(term);
				return ResponseEntity.ok(term);
			} else {
				return ResponseEntity.badRequest().body(valid.verifyViolations(term));  
			} 
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(response);
		}
	}
}
