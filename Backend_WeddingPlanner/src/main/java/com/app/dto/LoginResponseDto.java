package com.app.dto;


	public class LoginResponseDto {
	    private String token;
	    private UserDto user;

	    // Getters and setters
	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public UserDto getUser() {
	        return user;
	    }

	    public void setUser(UserDto user) {
	        this.user = user;
	    }
	}


