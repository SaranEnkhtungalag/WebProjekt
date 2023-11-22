package htwberlin.webtech_Projekt.web.Entities;

import jakarta.persistence.*;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name= "shop_id", referencedColumnName = "id")
    private ShoppingList shopid;


    @ManyToOne // Many items can belong to one category
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    private CategoryEntity categoryID;


    public ItemEntity(Long itemID, String itemName, int quantity /*, ShoppingList shopid */, CategoryEntity categoryID) {
      this.itemID = itemID;
      this.itemName = itemName;
      this.quantity = quantity;
      //this.shopid = shopid;
      this.categoryID = categoryID;
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

    /*
    public ShoppingList getShopid() {
        return shopid;
    }

    public void setShopid(ShoppingList shopid) {
        this.shopid = shopid;
    }

     */


    public int getQuantity() {
      return quantity;
   }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
}
