package com.booksapp.controllers.forms;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountForm {
    private Integer id;

    @NotBlank(message = "Emailを入力してください")
    @Email
    private String email;

    @Valid
    private PasswordForm password;

    @NotBlank(message = "ユーザー名を入力してください")
    private String name;
    
    private String description;

    public String toString() {
        return "email: " + this.email
               + " password: " + this.password
               + " name: " + this.name
               + " description: " + this.description;
    }
}
