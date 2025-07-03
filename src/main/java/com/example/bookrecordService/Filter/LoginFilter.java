package com.example.bookrecordService.Filter;

import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
            AuthenticationException, IOException{
        try {
            // 이메일, 비밀번호, 이메일인증 true 등 기타 인증 로직

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
            //token 검증을 위한 AuthenticationManager로 전달
            return this.getAuthenticationManager().authenticate(authToken);
        } catch (IOException e) {
            throw new AuthenticationServiceException("인증을 시도하는 동안 오류가 발생했습니다", e);
        }
    }

    @Override
    // 로그인 성공 시 실행하는 메소드(JWT 발급)
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
        throws IOException, ServletException {

        String email = authentication.getName();
        User userByEmail = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));
        String userId = userByEmail.getId().toString();
        //권한을 문자열로 변환
        String role = extractAuthority(authentication);

        //토큰 종류(카테고리), 유저이름, 역할 등을 페어로드에 담는다.
        String newAccess = jwtUtil.createAccessToken("access", userId, role);
        String newRefresh = jwtUtil.createRefreshToken("refresh", userId, role);

        //리프레쉬 토큰 관리권한은 서버에 있다.
        saveOrUpdateRefreshEntity(userByEmail, newRefresh);

        response.setCharacterEncoding("UTF-8");
        //로그인 성공시
        setTokenResponse(response, newAccess, newRefresh);
        //로그인 성공에 대한 추가 정보를 response body에 담음
        response.getWriter().write("로그인에 성공했습니다.");
    }
}
