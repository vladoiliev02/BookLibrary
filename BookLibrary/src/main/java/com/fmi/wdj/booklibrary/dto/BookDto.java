package com.fmi.wdj.booklibrary.dto;

import com.fmi.wdj.booklibrary.model.book.Genre;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class BookDto {
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @NotEmpty(message = "Title cannot be empty")
    private String ISBN;

    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Author cannot be null")
    @NotBlank(message = "Author cannot be blank")
    @NotEmpty(message = "Author cannot be empty")
    private String author;

    @NotNull(message = "Genre cannot be null")
    private Set<Genre> genre;
}
