package com.muxi.apirest.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.muxi.apirest.model.Terminal;

public class VerifyViolations {
	
	@Autowired
	private Validator validator;
	
public List<String> verifyViolations(Terminal terminal){
		
		Set<ConstraintViolation<Terminal>> violations = validator.validate(terminal);
		List<String> errors = new ArrayList<String>();
		
		if (violations.size() == 0) 
        {	        	
			errors = null;
        	
        } else {	  

        	for (ConstraintViolation<Terminal> v : violations) {
        		 errors.add(v.getMessage());
        	}
        }
		return errors;
	}

}
