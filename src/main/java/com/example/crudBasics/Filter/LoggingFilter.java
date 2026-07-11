package com.example.crudBasics.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Order(2)

public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestId = (String) req.getAttribute("requestId");
        String id = requestId == null ? "NA" : requestId;

        System.out.println("[Request Start] id=" + id
                + " method=" + req.getMethod()
                + " uri=" + req.getRequestURI());

        chain.doFilter(request, response);

        System.out.println("[Request End] id=" + id
                + " status=" + res.getStatus());
    }
}
