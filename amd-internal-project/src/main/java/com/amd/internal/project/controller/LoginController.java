package com.amd.internal.project.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amd.internal.project.authentication.TokenUtil;
import com.amd.internal.project.authentication.resource.AuthenticationException;
import com.amd.internal.project.authentication.resource.TokenRequest;
import com.amd.internal.project.authentication.resource.TokenResponse;
import com.amd.internal.project.dto.UserDto;
import com.amd.internal.project.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest)
			throws AuthenticationException {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDto userDetails = (UserDto) userService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new TokenResponse(token));
	}

	@RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDto user = (UserDto) userService.loadUserByUsername(username);
		if (jwtTokenUtil.canTokenBeRefreshed(token)) {

			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new TokenResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		}
	}
}
