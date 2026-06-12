package com.emcode.tabz.config;

import com.emcode.tabz.model.Shop;
import com.emcode.tabz.repository.ShopRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShopTokenFilter extends OncePerRequestFilter {

    private final ShopRepo shopRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Alleen actief voor het upload endpoint
        if (!request.getRequestURI().startsWith("/api/tab/shop")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        String rawToken = authHeader.substring(7);

        // Zoek alle shops op en check welke shop bij deze token hoort
        List<Shop> shops = shopRepo.findAll();
        Shop matchedShop = shops.stream()
                .filter(shop -> passwordEncoder.matches(rawToken, shop.getTokenHash()))
                .findFirst()
                .orElse(null);

        if (matchedShop == null || !matchedShop.isActive()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or inactive shop token");
            return;
        }

        // Zet de shop als authenticated principal in de SecurityContext
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        matchedShop,
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_SHOP"))
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
