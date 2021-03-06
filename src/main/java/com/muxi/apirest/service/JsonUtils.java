package com.muxi.apirest.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JsonUtils {
	public JSONObject stringToJson(String[] array) {
		
			JSONObject p1 = new JSONObject();
			p1.put("logic", Integer.parseInt(array[0]));
			p1.put("serial", array[1]);
			p1.put("model", array[2]);
			p1.put("sam", Integer.parseInt(array[3]));
			p1.put("ptid", array[4]);
			p1.put("plat", Integer.parseInt(array[5]));
			p1.put("version", array[6]);
			p1.put("mxr", Integer.parseInt(array[7]));
			p1.put("mxf", array[8]);
			if(array.length == 10)
				p1.put("verfm", array[9]);
		
			return p1;
	}
}
