package htwberlin.webtech_Projekt.web.api;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingListRestController {

    @Autowired
    ShoppingListService service;

    Logger logger = LoggerFactory.getLogger(ShoppingListRestController.class);

    @PostMapping("/shoppingList")
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return service.save(shoppingList);
    }

    @GetMapping("/shoppingList/{id}")
    public ShoppingList getShoppinList(@PathVariable String id) {
      logger.info("Get ...", id);
      Long shoppingId = Long.parseLong(id);
      return service.get(shoppingId);
    }
}
