package com.userSphere.security;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userSphere.pojos.LoginUserRequestModel;
import com.userSphere.repositories.UsersRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom authentication filter for processing login requests and generating JWT tokens upon successful authentication.
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	UsersRepo usersRepo;
	
	// Constructor to inject AuthenticationManager and UsersRepo
	public AuthenticationFilter(AuthenticationManager authenticationManager, UsersRepo usersRepo) {
		super(authenticationManager);
		this.usersRepo = usersRepo;
	}
	
	/**
     * This method is triggered when the user attempts to log in.
     * It reads the login credentials from the request body and performs authentication.
     */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			// Convert incoming JSON login request to LoginUserRequestModel
			LoginUserRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginUserRequestModel.class);
			
			// Authenticate using Spring Security
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getMailId(), // using email as the username
							creds.getPassword(),
							new ArrayList<>() // No roles passed in here
					)
			);
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * This method is called when authentication is successful.
     * It generates a JWT token and adds it to the response header.
     */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// Get authenticated username
		String username = ((UsersPrincipal)authResult.getPrincipal()).getUsername();
		
		// Define a secret key (should ideally be stored securely in application properties or environment variables)
		String commonKey = "6pJx@8YdZ#rMfN2!tBvAqLz9WuXkE0sG^CiTpOhKmRvLdFgQeJnUySbHwMzXaVcB";
		SecretKey secretKey = Keys.hmacShaKeyFor(commonKey.getBytes());
		
		// Create JWT token
		String jwtToken = Jwts.builder()
				.claim("scope", authResult.getAuthorities()) // Add user roles/authorities
				.subject(username)
				.expiration(Date.from(Instant.now().plusMillis(24000000))) // Token expiration time
				.issuedAt(Date.from(Instant.now())) // Token issue time
				.signWith(secretKey) // Sign the token
				.compact();
		
		// Add token to the Authorization header of the response
		response.setHeader("Authorization", "Bearer "+jwtToken);
	}
}
