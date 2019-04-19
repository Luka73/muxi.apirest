package com.muxi.apirest.service;

public class ConstantPaths {
	public interface API {
		public static final String URL_POST = "/terminal";
		public static final String URL_GET = "/terminal/{id}";
		public static final String URL_PUT = "/terminal/{id}";
		
		public interface V1 {
			public static final String URL_API_VERSION = "/v1";
		} 
	}
}
