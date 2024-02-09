package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "items")
    private String[] items;

    private Category() {}

    public Category(String name, String[] items) {
        this.name = name;
        this.items = items;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String[] getItems() {
        return this.items;
    }
}
