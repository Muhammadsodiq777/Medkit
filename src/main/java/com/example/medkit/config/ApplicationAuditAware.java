package com.example.medkit.config;

import com.example.medkit.model.entity.User;
import com.example.medkit.repository.UserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationAuditAware implements AuditorAware<User> {
    private final UserRepository userRepository;

    public ApplicationAuditAware(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.empty();
        }

        String phoneNumber = (String) authentication.getPrincipal();
        return Optional.ofNullable(userRepository.findByPhoneNumber(phoneNumber));
    }
}
