package model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
}
