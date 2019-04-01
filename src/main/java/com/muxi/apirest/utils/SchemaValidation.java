package com.muxi.apirest.utils;

import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;

public class SchemaValidation {

	public boolean validaJson(JSONObject json) {
		
		try (InputStream inputStream = new ClassPathResource("schema.json").getInputStream()) {
			  JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			  Schema schema = SchemaLoader.load(rawSchema);
			  schema.validate(json);
			  return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}  
}
