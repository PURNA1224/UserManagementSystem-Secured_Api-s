package com.userSphere.constants;

import com.userSphere.SpringApplicationContext;
import com.userSphere.security.AppProperties;

/**
 * SecurityConstants is a utility class that holds constants and utility methods
 * related to security configuration, such as token headers and dynamic security values.
 */
public final class SecurityConstants {
	
	// The HTTP header used to pass the JWT token in requests
	public static final String HEADER_TOKEN_KEY = "Authorization";
	
	// The prefix expected before the token value (e.g., "Bearer <token>")
	public static final String HEADER_TOKEN_PREFIX = "Bearer ";
	
	/**
     * Fetches the secret key used for signing JWTs from the Spring application context.
     * This allows the key to be managed via external configuration.
     *
     * @return A String representing the JWT signing key.
     */
	public static String getCommonKey() {
		return ((AppProperties)SpringApplicationContext.getBean("appProperties")).getCommonKey();
	}
	
	/**
     * Retrieves the JWT expiration time from application properties.
     * Useful for controlling token validity duration without hardcoding it.
     *
     * @return A String representing the expiration time (usually in milliseconds).
     */
	public static String getExprirationTime() {
		return ((AppProperties)SpringApplicationContext.getBean("appProperties")).getExpireTime();
	}
}
