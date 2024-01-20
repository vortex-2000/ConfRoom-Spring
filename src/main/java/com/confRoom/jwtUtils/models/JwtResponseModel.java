package com.confRoom.jwtUtils.models;

public class JwtResponseModel {

	 private static final long serialVersionUID = 1L;
	   private final String token;
	   public JwtResponseModel(String token) {
	      this.token = token;
	   }
	   public String getToken() {
	      return token;
	   }
}
