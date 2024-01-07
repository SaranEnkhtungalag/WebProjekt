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
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
public class ShoppingListRestController {


    @Autowired
    private ShoppingListService shoppingListService;

    Logger logger = LoggerFactory.getLogger(ShoppingListRestController.class);

    @PostMapping("/shoppingLists")
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.save(shoppingList);
    }

//    @PostMapping("/shoppingListV2")
//    public ResponseEntity<ShoppingList> createShoppingListV2(@RequestBody ShoppingList shoppingListEntity) {
//        try {
//            ShoppingList createdShoppingList = service.save(shoppingListEntity);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdShoppingList);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    // to see all the shopping lists
    @GetMapping("/shoppingLists")
    public ResponseEntity<List<ShoppingList>> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();
        return ResponseEntity.ok(shoppingLists);
    }

/*
    @GetMapping("/shoppingLists/{id}")
    public ResponseEntity<?> getShoppingList(@PathVariable Long id) {
        try {
            ShoppingList shoppingList = shoppingListService.get(id);
            return ResponseEntity.ok(shoppingList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The shopping list with ID " + id + " doesn't exist");
        }
    }

 */


/*
    @GetMapping("/shoppingLists/{id}")
    public ResponseEntity<?> getShoppingList(@PathVariable Long id) {
        try {
            ShoppingList shoppingList = shoppingListService.get(id);
            List<ItemEntity> items = shoppingList.getItems();
            // Now, 'items' contains the associated items for the shopping list
            return ResponseEntity.ok(shoppingList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The shopping list with ID " + id + " doesn't exist");
        }
    }

 */


/*
    @GetMapping("/shoppingLists/{id}")
    public ResponseEntity<?> getShoppingList(@PathVariable Long id) {
        try {
            ShoppingList shoppingList = shoppingListService.get(id);
            List<ItemEntity> items = shoppingList.getItems();
            logger.info("Fetched items for shopping list {}: {}", id, items);
            return ResponseEntity.ok(shoppingList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("The shopping list with ID " + id + " doesn't exist");
        }
    }

 */

    //WORKING ON
    @GetMapping("/shoppingLists/{id}")
    public ResponseEntity<?> getShoppingList(@PathVariable Long id) {
        try {
            ShoppingList shoppingList = shoppingListService.get(id);

            // Eagerly fetch items and categories
            shoppingList.getItems().forEach(item -> {
                item.getcategoryID(); // Eagerly fetch category for each item
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

    /*
    //Working on
    @PostMapping("/shoppingLists/{id}/weeklyCleanup")
    public ResponseEntity<String> performWeeklyCleanup(@PathVariable Long id) {
        try {
            service.performWeeklyCleanup();
            return ResponseEntity.ok("Weekly cleanup performed successfully");
        } catch (Exception e) {
            logger.error("Error performing weekly cleanup", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error performing weekly cleanup");
        }
    }
     */


}
