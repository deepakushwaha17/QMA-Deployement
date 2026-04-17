package com.app.quantitymeasurement.response;

public class AuthResponse {
	private String token;

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}