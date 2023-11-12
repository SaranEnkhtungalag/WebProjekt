package htwberlin.webtech_Projekt.web.Entities;

import jakarta.persistence.*;

@Entity
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long itemID;
    @Column(name = "name")
    private String name;
    //@Column(name = "category")
 //  private Category idCategory;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name= "shop_id", referencedColumnName = "id")
    private ShoppingList shopid;

    public ItemEntity(Long itemID, String name, /*Category idCategory, */int quantity, ShoppingList shopid) {
      this.itemID = itemID;
      this.name = name;
   //   this.idCategory = idCategory;
      this.quantity = quantity;
      this.shopid = shopid;
    }

    public ItemEntity() { }

    public Long getItemID() {
      return itemID;
   }

    public String getName() {
      return name;
   }

    public void setName(String name) {
      this.name = name;
   }
/*
   public Category getIdCategory() {
      return idCategory;
   }

   public void setIdCategory(Category idCategory) {
      this.idCategory = idCategory;
   }
*/

    public ShoppingList getShopid() {
        return shopid;
    }

    public void setShopid(ShoppingList shopid) {
        this.shopid = shopid;
    }

    public int getQuantity() {
      return quantity;
   }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
}
