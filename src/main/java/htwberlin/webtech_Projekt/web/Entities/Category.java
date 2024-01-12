package htwberlin.webtech_Projekt.web.Entities;

//@Entity
public class Category {

    private Long categoryID;
    private String categoryName;

    public Category() {
    // default constructor
    }

    public Category(Long categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
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

