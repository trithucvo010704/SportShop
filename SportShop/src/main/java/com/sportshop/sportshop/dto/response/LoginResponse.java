package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.String;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private boolean login;
    private RoleEnum role;
    private String message;
}
