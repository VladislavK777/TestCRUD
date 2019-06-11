package com.vladislavk.testcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 10.06.2019
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private String username;
    private String password;
}
