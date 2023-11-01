package htwberlin.webtech_Projekt.web.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

    private Long idShoppingList;
    private String shoppingName;
    private List<Item> items = new ArrayList<>();
    private boolean done;
    private LocalDate deadline;

    public ShoppingList(Long idShoppingList, String shoppingName, List<Item> items, boolean done, LocalDate deadline) {
        this.idShoppingList = idShoppingList;
        this.shoppingName = shoppingName;
        this.items = items;
        this.done = done;
        this.deadline = deadline;
    }

    public Long getIdShoppingList() {
        return idShoppingList;
    }

    public void setIdShoppingList(Long idShoppingList) {
        this.idShoppingList = idShoppingList;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
