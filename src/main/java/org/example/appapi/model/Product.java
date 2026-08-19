package org.example.appapi.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@AllArgsConstructor //Constructor have parameter
@NoArgsConstructor //Default Constructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Category category;
    private String code;
    private String name;
    private String description;
    private double qtyOnHand;
    @Column(length = 1)
    private String stockType; // I (Inventory / Stockable Item) N (Non-Stock / Non-Inventory)
    @Column(length = 3)
    private String status;

}
