package htwberlin.webtech_Projekt.web.Entities;

public class Item {

    private Long itemID;
    private String itemName;
    private ShoppingList idShoppingList;

    private CategoryEntity categoryID;

    private int quantity;

    private boolean done;

    public Item(Long itemID, String itemName, int quantity, ShoppingList idShoppingList , CategoryEntity categoryID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.idShoppingList = idShoppingList;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


}
