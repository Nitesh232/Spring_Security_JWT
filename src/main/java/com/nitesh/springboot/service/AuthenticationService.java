package com.nitesh.springboot.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitesh.springboot.entity.AuthenticationRequest;
import com.nitesh.springboot.entity.AuthenticationResponse;
import com.nitesh.springboot.entity.User;
import com.nitesh.springboot.repo.UserRepo;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	public AuthenticationResponse register(User request) {
		
		request.setPassword(encoder.encode(request.getPassword()));
				
		repo.save(request);
		
		String generateToken = jwtService.generateToken(request);
		
		return AuthenticationResponse
				.builder()
				.token(generateToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
		
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
		
		if(authenticate.isAuthenticated()) {
			
			String generateToken = jwtService.generateToken(request.getName());			
			return AuthenticationResponse
					.builder()
					.token(generateToken)
					.build();
		}else {
			throw new UserPrincipalNotFoundException("User "+request.getName()+" not found");
			
		}
	}

}
