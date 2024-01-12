package htwberlin.webtech_Projekt.web.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ItemEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemID")
    private Long itemID;
    @Column(name = "itemName")
    private String itemName;

    @Column(name = "quantity")
    private int quantity;


    @ManyToOne
    @JoinColumn(name= "idShoppingList", referencedColumnName = "idShoppingList")
    @JsonBackReference
    private ShoppingList idShoppingList;


    @ManyToOne // Many items can belong to one category
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    //@JsonBackReference
    private CategoryEntity categoryID;

    @Column(name = "done")
    private Boolean done;

/*
    public ItemEntity(Long itemID, String itemName, int quantity , ShoppingList shopid , CategoryEntity categoryID) {
      this.itemID = itemID;
      this.itemName = itemName;
      this.quantity = quantity;
      this.shopid = shopid;
      this.categoryID = categoryID;
      done = false;
    }

 */

    //WORKING ON
    public ItemEntity(Long itemID, String itemName, int quantity , ShoppingList idShoppingList , CategoryEntity categoryID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.idShoppingList = idShoppingList;
        this.categoryID = categoryID;
        done = false;
    }


    public ItemEntity() { }

    public Long getItemID() {
      return itemID;
   }

    public String getitemName() {
      return itemName;
   }

    public void setitemName(String itemName) {
      this.itemName = itemName;
   }

   public CategoryEntity getcategoryID() {
      return categoryID;
   }

   public void setcategoryID(CategoryEntity categoryID) {
      this.categoryID = categoryID;
   }


    public ShoppingList getidShoppingList() {
        return idShoppingList;
    }

    public void setShopid(ShoppingList shopid) {
        this.idShoppingList = shopid;
        if (shopid != null) {
            shopid.getItems().add(this); // Update the other side of the relationship
        }
    }


    public int getQuantity() {
      return quantity;
   }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

    public Boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
