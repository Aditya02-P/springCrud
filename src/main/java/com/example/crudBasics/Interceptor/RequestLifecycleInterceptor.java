package com.example.crudBasics.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class RequestLifecycleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTimeMs", System.currentTimeMillis());

        String handlerName = handler instanceof HandlerMethod handlerMethod
                ? handlerMethod.getMethod().getName()
                : handler.getClass().getSimpleName();

        System.out.println("[Interceptor PreHandle] method=" + request.getMethod()
                + " uri=" + request.getRequestURI()
                + " handler=" + handlerName);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Object start = request.getAttribute("startTimeMs");
        if (start instanceof Long startTime) {
            long durationMs = System.currentTimeMillis() - startTime;
            System.out.println("[Interceptor AfterCompletion] method=" + request.getMethod()
                    + " uri=" + request.getRequestURI()
                    + " status=" + response.getStatus()
                    + " durationMs=" + durationMs);
        }
    }
}
