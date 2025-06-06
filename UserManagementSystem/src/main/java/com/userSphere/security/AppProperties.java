package com.userSphere.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * AppProperties is a Spring component that provides access
 * to application properties defined in the environment or property files.
 * 
 * This class helps in retrieving values like security keys and token expiration times.
 */
@Component
public class AppProperties {

    @Autowired
    private Environment environment;
    
    /**
     * Retrieves the common security key used for token generation/validation.
     * 
     * @return the common key string from application properties.
     */
    public String getCommonKey() {
        return environment.getProperty("token.commonKey");
    }
    
    /**
     * Retrieves the token expiration time configured in the application.
     * 
     * @return the expiration time as a string from application properties.
     */
    public String getExpireTime() {
        return environment.getProperty("time.expiration");
    }
}
