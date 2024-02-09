package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;


    @Column(name = "expiration_date")
    private LocalDate expirationDate;


    @Column(name = "unit")
    private Integer unit;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private String category;

    private Ingredient() {}

    public Ingredient(String name, LocalDate expirationDate, Integer unit, String category) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.unit = unit;
        this.category = category;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public Integer getUnit() {
        return this.unit;
    }

    public String getCategory() {
        return this.category;
    }
}
