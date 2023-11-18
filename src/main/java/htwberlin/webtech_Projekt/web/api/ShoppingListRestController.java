package htwberlin.webtech_Projekt.web.api;
//This class is a REST controller responsible for handling HTTP requests related to shopping lists.

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
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
    ShoppingListService service;

    Logger logger = LoggerFactory.getLogger(ShoppingListRestController.class);

    @PostMapping("/shoppingLists")
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return service.save(shoppingList);
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
        List<ShoppingList> shoppingLists = service.getAllShoppingLists();
        return ResponseEntity.ok(shoppingLists); //.ok erzeugt eine Rückgabe
    }

    @GetMapping("/shoppingLists/{id}")
    public ShoppingList getShoppingList(@PathVariable String id) {
    //  logger.info("Get ...", id);
      Long shoppingId = Long.parseLong(id);
      return service.get(shoppingId);
    }


    @DeleteMapping("/shoppingLists/{id}")
    public ResponseEntity<String> deleteShoppingList(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Shopping list item deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting shopping list item");
        }
    }

    @PutMapping("/shoppingLists/{id}")
    public ResponseEntity<ShoppingList> updateShoppingList(
            @PathVariable Long id,
            @RequestBody ShoppingList updatedShoppingList) {
        ShoppingList updatedItem = service.update(id, updatedShoppingList);
        return ResponseEntity.ok(updatedItem);
    }

}
