package dev.patika.schoolmanagementsystem.core.interceptors;

import dev.patika.schoolmanagementsystem.core.utils.ClientRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ClientRequestInterceptor implements HandlerInterceptor {

    private final ClientRequestInfo clientRequestInfo;

    @Autowired
    public ClientRequestInterceptor(ClientRequestInfo clientRequestInfo) {
        this.clientRequestInfo = clientRequestInfo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        clientRequestInfo.setClientUrl(request.getRequestURI());
        clientRequestInfo.setClientIpAddress(request.getRemoteAddr());
        clientRequestInfo.setSessionActivityId(request.getSession().getId());
        return true;
    }
}
