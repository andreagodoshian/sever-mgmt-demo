package com.freyaselberg.serverdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotEmpty(message = "IP address can't be empty or null")
    private String ipAddress;

    private String name;
    private String memory;
    private String type;
    private String imgUrl;
    private Status status;
}