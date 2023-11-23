package htwberlin.webtech_Projekt.web.Entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryID", nullable = false)
    private Long categoryID;

    @Column(name = "categoryName", nullable = false)
    private String categoryName;


    @OneToMany(mappedBy = "categoryID", cascade = CascadeType.ALL)
    private List<ItemEntity> items;

    public CategoryEntity(Long categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    public CategoryEntity(Long categoryID) {
        this.categoryID = categoryID;
    }


    public CategoryEntity() {
    }


    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



}
