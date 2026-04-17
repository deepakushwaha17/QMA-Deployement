package com.app.quantitymeasurement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.request.LoginRequest;
import com.app.quantitymeasurement.request.SignupRequest;
import com.app.quantitymeasurement.response.AuthResponse;
import com.app.quantitymeasurement.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest request) {
		return authService.login(request);
	}

	@PostMapping("/signup")
	public AuthResponse signup(@RequestBody SignupRequest request) {
		return authService.signup(request);
	}
}