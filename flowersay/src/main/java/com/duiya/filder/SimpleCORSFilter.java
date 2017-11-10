package com.duiya.filder;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    	 HttpServletResponse response = (HttpServletResponse) res;
         HttpServletRequest request=(HttpServletRequest)req;
         response.setContentType("textml;charset=UTF-8");
         response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
         response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
         response.setHeader("Access-Control-Max-Age", "0");
         response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
         response.setHeader("Access-Control-Allow-Credentials", "true");
         response.setHeader("XDomainRequestAllowed","1");
         chain.doFilter(req,res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
