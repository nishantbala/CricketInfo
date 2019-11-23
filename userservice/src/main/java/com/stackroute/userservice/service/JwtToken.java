package com.stackroute.userservice.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.stackroute.userservice.config.Constants;
import com.stackroute.userservice.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {

	public String generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.compact();
		return jwtToken;
	}
}
