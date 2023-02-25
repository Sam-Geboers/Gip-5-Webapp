package be.ucll.gip5.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.isBlank()
                || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.split(" ")[1].trim();
        if (!jwtUtil.validate(token)) {
            // if token is not valid, then skip this filter and continue to execute next filter class.
            // This means authentication is not successful since token is invalid.
            filterChain.doFilter(request, response);
            return;
        }
        // Authorization header exists, token is valid. So, we can authenticate.
        String username = jwtUtil.getUsername(token);
        // initializing UsernamePasswordAuthenticationToken with its 3 parameter constructor
        // because it sets super.setAuthenticated(true); in that constructor.
        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        // finally, give the authentication token to Spring Security Context
        SecurityContextHolder.getContext().setAuthentication(upToken);

        // end of the method, so go for next filter class
        filterChain.doFilter(request, response);
    }
}
