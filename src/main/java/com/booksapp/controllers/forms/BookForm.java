package com.booksapp.controllers.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BookForm {
    @NotEmpty(message = "タイトルは入力必須です")
    @Email
    private String title;

    @NotBlank(message = "筆者は入力必須です")
    private String author;
    private String description;

    public String toString() {
        return "title: " + this.title
               + " author: "+ this.author
               + " description: " + this.description;
    }
}
