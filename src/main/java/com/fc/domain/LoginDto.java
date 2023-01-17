package com.fc.domain;

import lombok.Data;

/**
 * @author yfc
 * @date 2022/12/6 10:45
 */
@Data
public class LoginDto {

    private String username;

    private String password;

    private boolean rememberMe = false;

}
