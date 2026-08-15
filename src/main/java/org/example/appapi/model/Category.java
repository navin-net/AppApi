package org.example.appapi.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String namekh;
    @Column(length = 3)
    private String status;

}
