package com.example.moneymind.config.security;

import com.example.moneymind.config.exception.MoneyMindException;
import com.example.moneymind.dtos.authentication.JwtTokenDTO;
import com.example.moneymind.entidades.Usuario;
import com.example.moneymind.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

import static com.example.moneymind.config.exception.MensagemDeExcecao.USUARIO_NAO_ENCONTRADO;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {

            if (request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/user/register")) {
                filterChain.doFilter(request, response);
                return;
            }
            var token = this.recoverToken(request);
            var login = tokenService.validateToken(token);

            if (login != null) {
                Usuario user = usuarioRepository.findByEmail(login.getEmail())
                        .orElseThrow(() -> new MoneyMindException(HttpStatus.NOT_FOUND, USUARIO_NAO_ENCONTRADO));
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

                var jwtTokenDTO = new JwtTokenDTO(user.getId(), user.getEmail(), user.getUsername());

                var authentication = new UsernamePasswordAuthenticationToken(jwtTokenDTO, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            filterChain.doFilter(request, response);
        } catch (MoneyMindException e) {
            throw e;
        } catch (Exception e) {
            throw new MoneyMindException(HttpStatus.UNAUTHORIZED, "Token inválido");
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}