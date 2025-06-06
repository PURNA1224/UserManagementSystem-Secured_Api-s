package com.userSphere.security;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.userSphere.constants.SecurityConstants;
import com.userSphere.entities.UserEntity;
import com.userSphere.repositories.UsersRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom authorization filter to validate JWT tokens in request headers and set security context.
 */
public class AuthorizationFilter extends BasicAuthenticationFilter{

	UsersRepo usersRepo;
	// Constructor to inject dependencies
	public AuthorizationFilter(AuthenticationManager authenticationManager, UsersRepo usersRepo) {
		super(authenticationManager);
		this.usersRepo = usersRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getHeader(SecurityConstants.HEADER_TOKEN_KEY);
		if(token == null || !token.startsWith(SecurityConstants.HEADER_TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthentication(token.substring(7).trim());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
		String commonKey = SecurityConstants.getCommonKey();
		SecretKey secretKey = Keys.hmacShaKeyFor(commonKey.getBytes());
		
		Claims claims = Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		UserEntity userEntity = usersRepo.findByUserName(claims.getSubject());
		
		if(userEntity != null) {
			UsersPrincipal usersPrincipal = new UsersPrincipal(userEntity);
			return new UsernamePasswordAuthenticationToken(usersPrincipal, null, usersPrincipal.getAuthorities());
		}
		return null;
	}
}
