package com.ca.prism.cop.middleware;

import static java.lang.String.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import lombok.extern.java.*;

@Component
@Log
public class TimingMiddleware extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long start = System.currentTimeMillis();
		filterChain.doFilter(request, response);
		long end = System.currentTimeMillis();
		log.info(format("%s Request to %s took %s milliseconds", request.getMethod(), request.getRequestURI() , (end - start)));
	}

}