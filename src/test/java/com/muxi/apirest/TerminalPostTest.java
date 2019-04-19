package com.muxi.apirest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.muxi.apirest.service.ConstantPaths;
import com.muxi.apirest.service.RequestFlows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TerminalPostTest {
	
	RequestFlows request = new RequestFlows(); 
	String postRequest = ConstantPaths.API.V1.URL_API_VERSION + ConstantPaths.API.URL_POST;
	String getRequest = ConstantPaths.API.V1.URL_API_VERSION + ConstantPaths.API.URL_GET;
	
	@Test
	public void testPostTerminal() {
		String corpo = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";
		request.okFlow(corpo, postRequest);
	}
	
	@Test
	public void testInvalidFormat() {
		String corpo = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3";
		request.badRequestFlow(corpo, postRequest);
	}
	
	@Test
	public void testMissedLogicField() {
		String corpo = ";44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN;";
		request.badRequestFlow(corpo, postRequest);
	}
	
	@Test
	public void testMissedSerialField() {
		String corpo = "44332211;;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN;";
		request.badRequestFlow(corpo, postRequest);
	}
	
	@Test
	public void testMissedModelField() {
		String corpo = "44332211;123;;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN;";
		request.badRequestFlow(corpo, postRequest);
	}
	
	@Test
	public void testMissedVersionField() {
		String corpo = "44332211;123;PWWIN;0;F04A2E4088B;4;;0;16777216;PWWIN;";
		request.badRequestFlow(corpo, postRequest);	
	}
}
