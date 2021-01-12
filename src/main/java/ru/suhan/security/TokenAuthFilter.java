package ru.suhan.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.suhan.domain.token.Token;
import ru.suhan.service.token.TokenService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenAuthFilter implements Filter {

    private final TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getCookies() != null) {
            Optional<Cookie> authToken = Arrays
                    .stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("Auth-Token"))
                    .findFirst();
            if (authToken.isPresent()) {
                TokenAuthentication tokenAuthentication = new TokenAuthentication(authToken.get().getValue());
                Token token = tokenService.findByValue(tokenAuthentication.getName());
                if (token.isExist()) {
                    UserDetails userDetails = new UserDetailsImpl(token.user());
                    tokenAuthentication.setUserDetails(userDetails);
                    tokenAuthentication.setAuthenticated(true);
                    SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                }
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
