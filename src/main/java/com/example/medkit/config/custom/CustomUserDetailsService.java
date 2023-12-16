package com.example.medkit.config.custom;

import com.example.medkit.model.entity.User;
import com.example.medkit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            user = userRepository.findByEmail(phoneNumber);
        }

        if (user == null)
            throw new UsernameNotFoundException("User not found");

        if (!user.isActive()) {
            throw new UsernameNotFoundException("User is not active");
        }
        return new CustomUserDetails(user);
    }
}
