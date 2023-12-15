package com.example.medkit.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveResponse {
    private Long id;

    public SaveResponse(Long id) {
        this.id = id;
    }
}
