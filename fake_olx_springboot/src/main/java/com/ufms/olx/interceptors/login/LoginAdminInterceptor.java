package com.ufms.olx.interceptors.login;

import com.ufms.olx.domain.entities.Usuario;
import com.ufms.olx.services.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginAdminInterceptor implements HandlerInterceptor {
    @Autowired
    UsuarioService service;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        String login = request.getHeader("login");
        String senha = request.getHeader("senha");

        Usuario usuario = service.login(login, senha);
        return usuario != null && usuario.isAdminstrador();
    }

    @Override
    public void postHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        ModelAndView modelAndView
    ) {}

    @Override
    public void afterCompletion(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        Exception ex
    ) {}
}
