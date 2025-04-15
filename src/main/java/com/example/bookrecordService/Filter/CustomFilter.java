package com.example.bookrecordService.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import java.io.IOException;
import jakarta.servlet.Filter;

@Slf4j
public class CustomFilter implements Filter{

    private static final String[] WHITE_LIST = {"/", "/users/signup", "/login", "/logout"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        //Filter에서 수행할 Logic
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");
        //WhiteList에 포함되지 않은 URL의 경우 해당 로직 수행
        if(!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if(session != null || session.getAttribute("email") == null){
                throw new RuntimeException("로그인 해주세요,");
            }

            //로그인 성공 로직
            log.info("로그인에 성공했습니다.");
        }

        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
