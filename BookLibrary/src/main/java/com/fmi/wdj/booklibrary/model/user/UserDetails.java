package com.fmi.wdj.booklibrary.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "details_id", nullable = false)
    private long id;

    @NotNull(message = "E-mail cannot be null.")
    @NotEmpty(message = "E-mail cannot be empty.")
    @NotBlank(message = "E-mail cannot be blank.")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String country;

    private String city;

    private String street;
}
