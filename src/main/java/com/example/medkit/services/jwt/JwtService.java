package com.example.medkit.services.jwt;

import com.example.medkit.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    public String createAccessToken(User user, Date expirationDate) {
        return "";
    }

    public String createRefreshToken(Date expirationDate) {
        return "";
    }
}
