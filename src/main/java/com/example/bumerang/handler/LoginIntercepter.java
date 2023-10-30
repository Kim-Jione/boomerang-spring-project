package com.example.bumerang.handler;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(request.getRequestURI());

        String uri = request.getRequestURI();

        HttpSession session = request.getSession();
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            if (uri.contains("api")) {
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "로그인을 진행해주세요", null);
                ObjectMapper om = new ObjectMapper();
                String json = om.writeValueAsString(cmRespDto);
                out.println(json);
            } else {
                response.sendRedirect("/loginForm");
            }
            return false;
        }
        return true;
    }
}