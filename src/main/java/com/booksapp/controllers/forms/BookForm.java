package com.booksapp.controllers.forms;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BookForm {
    @NotBlank(message = "タイトルを入力してください")
    private String title;

    @NotBlank(message = "筆者を入力してください")
    private String author;

    private String description;

    public String toString() {
        return "title: " + this.title
               + " author: "+ this.author
               + " description: " + this.description;
    }
}
