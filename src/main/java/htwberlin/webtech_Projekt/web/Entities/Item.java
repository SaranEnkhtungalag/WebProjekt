package htwberlin.webtech_Projekt.web.Entities;

public class Item {

    private Long itemID;
    private String name;
    //  private Category idCategory;
    private int quantity;

    public Item(Long itemID, String name, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
