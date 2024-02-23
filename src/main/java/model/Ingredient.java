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
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "expiration_date")
    private LocalDate[] expirationDate;


    @Column(name = "unit")
    private Integer unit;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public Ingredient(String name, LocalDate[] expirationDate, Integer unit, Category category) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.unit = unit;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public LocalDate[] getExpirationDate() {
        return expirationDate;
    }

    public Integer getUnit() {
        return unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
}


