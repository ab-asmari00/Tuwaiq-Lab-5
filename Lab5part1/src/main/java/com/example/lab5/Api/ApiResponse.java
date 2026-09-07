package com.example.lab5.Api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String message;

}
