package com.fmi.wdj.booklibrary.model.user;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username", nullable = false, unique = true, length = 20)
    @Length(min = 6, max = 20, message = "The username should be between 6 and 20 characters long.")
    private String username;

    @NotNull(message = "User details cannot be null")
    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "details_id")
    private UserDetails details;
}
