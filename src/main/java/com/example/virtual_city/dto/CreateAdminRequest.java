package com.example.virtual_city.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateAdminRequest {

    private String email;
    private String name;
    private List<String> allowedModules;

}
