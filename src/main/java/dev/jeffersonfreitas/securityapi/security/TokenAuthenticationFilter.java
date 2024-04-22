package dev.jeffersonfreitas.securityapi.security;

import dev.jeffersonfreitas.securityapi.model.User;
import dev.jeffersonfreitas.securityapi.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getBearerAuthentication(request);

        if(token.isPresent()){

            String username = SecurityUtils.verifyToken(token.get());
            Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

            if(user.isEmpty()){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Username does not exists or password is invalid");
                return;
            }

            UserPrincipal principal = UserPrincipal.create(user.get());
            setAuthentication(principal);
        }
        filterChain.doFilter(request, response);
    }


    private void setAuthentication(UserPrincipal userPrincipal) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private Optional<String> getBearerAuthentication(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        return header != null && header.startsWith(BEARER) ?
                Optional.of(header.replace(BEARER, "")) : Optional.empty();
    }
}
