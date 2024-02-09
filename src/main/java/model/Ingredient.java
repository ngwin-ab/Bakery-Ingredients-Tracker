package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "unit")
    private Integer unit;

    private Ingredient() {}

    public Ingredient(String name, String category, LocalDate expirationDate, Integer unit) {
        this.name = name;
        this.category = category;
        this.expirationDate = expirationDate;
        this.unit = unit;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public Integer getUnit() {
        return this.unit;
    }

}
