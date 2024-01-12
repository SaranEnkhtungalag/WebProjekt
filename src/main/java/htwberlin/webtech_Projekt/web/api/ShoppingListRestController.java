package htwberlin.webtech_Projekt.web.api;
//This class is a REST controller responsible for handling HTTP requests related to shopping lists.

import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Service.CategoryService;
import htwberlin.webtech_Projekt.web.Service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import org.springframework.http.HttpStatus;
import javax.annotation.PostConstruct;
import java.time.LocalDate;


@RestController
@CrossOrigin(origins = "*")
public class ShoppingListRestController {

    @Autowired
    private ShoppingListService shoppingListService;

    Logger logger = LoggerFactory.getLogger(ShoppingListRestController.class);

/*
    // creates the default shopping list
    @PostConstruct
    public void initializeDefaultShoppingList() {
        // Check if the default shopping list already exists
        if (!shoppingListService.existsByName("The Shopping List")) {
            // Create the default shopping list
            ShoppingList defaultShoppingList = new ShoppingList();
            defaultShoppingList.setShoppingName("The Shopping List");
            defaultShoppingList.setDeadline(LocalDate.now());

            // Set other properties if needed
            shoppingListService.save(defaultShoppingList);
        }
    }

 */
 /*
    // creates the default shopping list
    @PostConstruct
    public void initializeDefaultShoppingList() {
        String defaultShoppingListName = "The Shopping List";

        if (!shoppingListService.existsByName(defaultShoppingListName)) {
            Long defaultShoppingListId = 1L;
            if (!shoppingListService.existsById(defaultShoppingListId)) {
                ShoppingList defaultShoppingList = new ShoppingList();
                defaultShoppingList.setShoppingName(defaultShoppingListName);
                defaultShoppingList.setDeadline(LocalDate.now());
                shoppingListService.save(defaultShoppingList);
            }
        }
    }

  */


    // creates the default shopping list
    @PostConstruct
    public void initializeDefaultShoppingList() {
        String defaultShoppingListName = "The Shopping List";

        // Set the deadline to the next Sunday
        LocalDate currentDeadline = LocalDate.now();
        LocalDate nextSunday = currentDeadline.plusDays(1).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        if (!shoppingListService.existsByName(defaultShoppingListName)) {
            Long defaultShoppingListId = 1L;
            if (!shoppingListService.existsById(defaultShoppingListId)) {
                ShoppingList defaultShoppingList = new ShoppingList();
                defaultShoppingList.setShoppingName(defaultShoppingListName);
                defaultShoppingList.setDeadline(nextSunday);
                shoppingListService.save(defaultShoppingList);
            }
        }



    }


    // to create a shopping list
    // only for backend --> no shopping list needs to be created as the default shopping list
    // will be created automatically
    @PostMapping("/shoppingLists")
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.save(shoppingList);
    }


    // to see all the shopping lists
    @GetMapping("/shoppingLists")
    public ResponseEntity<List<ShoppingList>> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();
        return ResponseEntity.ok(shoppingLists);
    }


    // to show a shopping list
    @GetMapping("/shoppingLists/{id}")
    public ResponseEntity<?> getShoppingList(@PathVariable Long id) {
        try {
            ShoppingList shoppingList = shoppingListService.get(id);
            shoppingList.getItems().forEach(item -> {
                item.getcategoryID();
            });
            logger.info("Fetched items for shopping list {}: {}", id, shoppingList.getItems());
            return ResponseEntity.ok(shoppingList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The shopping list with ID " + id + " doesn't exist");
        }
    }


    @PutMapping("/shoppingLists/{id}")
    public ResponseEntity<?> updateShoppingList(
            @PathVariable Long id,
            @RequestBody ShoppingList updatedShoppingList) {
        try {
            ShoppingList updatedItem = shoppingListService.update(id, updatedShoppingList);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The shoppinglist with ID " + id + " doesn't exist");
        }
    }


    // to delete a shopping list
    @DeleteMapping("/shoppingLists/{id}")
    public ResponseEntity<String> deleteShoppingList(@PathVariable Long id) {
        try {
            ShoppingList existingShoppingList = shoppingListService.findById(id);

            if (existingShoppingList == null) {
                // Item with the specified ID does not exist
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The shoppinglist with ID " + id + " doesn't exist");
            }
            shoppingListService.delete(id);
            return ResponseEntity.ok("Shoppinglist deleted successfully");
        } catch (RuntimeException e) {
            // Handle other runtime exceptions if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The shoppinglist with ID " + id +  " doesn't exist");
        }
    }

    @DeleteMapping("/shoppingLists/deleteAll")
    // to delete all shoppinglists
    public ResponseEntity<String> deleteAllShoppinglists() {
        shoppingListService.deleteAllShoppinglists();
        return ResponseEntity.ok("All shoppinglists were deleted successfully");
    }


    // trigger the update of the deadline manually if needed
    @PostMapping("/shoppingLists/updateForNextWeek")
    public ResponseEntity<String> updateShoppingListForNextWeek() {
        try {

            ShoppingList shoppingList = shoppingListService.findById(1L);
            logger.info("Current Deadline: {}", shoppingList.getDeadline());

            shoppingListService.updateShoppingListForNextWeek(1L);

            shoppingList = shoppingListService.findById(1L);
            logger.info("Updated Deadline: {}", shoppingList.getDeadline());

            return ResponseEntity.ok("Shopping List updated for next week successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Shopping List for next week");
        }
    }


}
