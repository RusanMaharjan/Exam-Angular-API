package com.exam.quiz.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.quiz.serviceImpl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			jwtToken = requestTokenHeader.substring(7); 
			try {
				username = jwtUtils.extractUsername(jwtToken);
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			} catch (Exception e) {
				System.out.println("Unable to get JWT token.");
			}
		} else {
			System.out.println("Invalid Token, not start with bearer.");
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		//validated		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtUtils.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("Token is not valid.");
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
