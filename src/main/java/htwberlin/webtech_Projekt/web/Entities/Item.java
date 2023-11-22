package htwberlin.webtech_Projekt.web.Entities;

public class Item {

    private Long itemID;
    private String itemName;
    private ShoppingList shopid;

    private CategoryEntity categoryID;

    private int quantity;

    public Item(Long itemID, String itemName, int quantity/*, ShoppingList shopid */, CategoryEntity categoryID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        //this.shopid = shopid;
        this.categoryID = categoryID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
