package com.sunbase.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    String jwt = request.getHeader(SecurityDetails.JWT_HEADER);
	    if (jwt != null && jwt.startsWith("Bearer ")) {
	        jwt = jwt.substring(7);
	        try {
	            SecretKey key = Keys.hmacShaKeyFor(SecurityDetails.JWT_KEY.getBytes());
	            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
	            String username = claims.get("username", String.class);
	            String authorities = claims.get("authorities", String.class);
	            Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
	                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
	            SecurityContextHolder.getContext().setAuthentication(auth);
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Invalid Token");
	            return;
	        }
	    }

	    filterChain.doFilter(request, response);
	}
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/signup");
    }
}