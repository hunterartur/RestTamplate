package com.arturishmaev.restTemplate.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

    private Long id;
    private String name;
    private String lastName;
    private byte age;

}
