package htwberlin.webtech_Projekt.web.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Working on

@Entity(name = "shoppingLists")
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idShoppingList")
    private Long idShoppingList;
    @Column(name = "name")
    private String shoppingName;

    //@Column(name = "done")
    //private Boolean done;
    @Column(name = "deadline")
    private LocalDate deadline;

    @OneToMany(mappedBy = "idShoppingList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("idShoppingList")
    private List<ItemEntity> items = new ArrayList<>();


    public ShoppingList() {
        // Default no-arg constructor
        this.items = new ArrayList<>();
    }


    public ShoppingList(Long idShoppingList, String shoppingName, boolean done, LocalDate deadline) {
        this.idShoppingList = idShoppingList;
        this.shoppingName = shoppingName;
        this.items = new ArrayList<>();
        //this.done = done;
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

    public List<ItemEntity> getItems() {
        return items;
    }
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    /*
    public Boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

     */

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }




}
