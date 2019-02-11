package com.amd.internal.project.authentication;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class UnAuthorizedResponseAuthentication implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = -8970718410437077606L;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
	 
    //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
   //     "You would need to provide the Jwt Token to Access This ressrce");
    //TODO - make it dynamic
	  response.sendRedirect("http://localhost:4200");
  }
}


