// 📄 File: dto/SetPasswordRequest.java
package com.example.virtual_city.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetPasswordRequest {
    private String email;
    private String password;
}
