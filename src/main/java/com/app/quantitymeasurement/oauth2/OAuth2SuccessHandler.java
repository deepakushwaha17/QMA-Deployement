package com.app.quantitymeasurement.oauth2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.quantitymeasurement.security.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JwtService jwtService;

	@Value("${oauth2.redirect-uri:http://localhost:3000/oauth2/callback}")
	private String redirectUri;

	public OAuth2SuccessHandler(JwtService jwtService) {
		super();
		this.jwtService = jwtService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
	        throws IOException {

	    OAuth2User user = (OAuth2User) auth.getPrincipal();

	    String username = user.getAttribute("email");
	    String token = jwtService.generateToken(username);

	    // Redirect to frontend callback page with token and email
	    String redirectUrl = redirectUri + "?token=" + token + "&email=" + username;
	    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}
}
