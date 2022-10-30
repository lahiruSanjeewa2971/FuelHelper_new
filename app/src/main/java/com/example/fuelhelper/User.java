package com.example.fuelhelper;

import java.time.LocalDateTime;

public class User {
    public Integer getRole() {
        return role;
    }

    private String name;
    private String email;
    private Integer role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
