package com.booksapp.controllers.forms;

import lombok.Data;

@Data
public class PasswordForm {
    private String password;

    public String toString() {
        return " password: " + this.password;
    }
}
