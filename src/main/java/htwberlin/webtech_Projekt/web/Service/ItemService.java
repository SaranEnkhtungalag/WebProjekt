package htwberlin.webtech_Projekt.web.Service;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Entities.CategoryEntity;
import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import htwberlin.webtech_Projekt.web.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;


@Service
public class ItemService {

    private final ItemRepository repo;
    private final CategoryService categoryService;  // Inject CategoryService
    private ShoppingListService shoppingListService;

    @Autowired
    public ItemService(ItemRepository repo, CategoryService categoryService) {
        this.repo = repo;
        this.categoryService =categoryService;
    }


    public List<ItemEntity> findAll() {
        return repo.findAll();
    }

    public ItemEntity findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

/*
    public ItemEntity save(ItemEntity item) {
        ItemEntity itemEntity = new ItemEntity(item.getItemID(), item.getitemName(), item.getQuantity(), item.getShopid(), item.getcategoryID());
        return mapToItem(repo.save(itemEntity));
    }

 */


    public ItemEntity save(ItemEntity item) {
        // Check if the item has a categoryID
        if (item.getcategoryID() != null) {
            // Fetch the CategoryEntity from the database based on the ID provided in the input item
            CategoryEntity categoryEntity = categoryService.findById(item.getcategoryID().getCategoryID());

            if (categoryEntity == null) {
                throw new RuntimeException("Category not found for ID: " + item.getcategoryID().getCategoryID());
            }
            // Create a new ItemEntity with the fetched CategoryEntity
            ItemEntity itemEntity = new ItemEntity(
                    item.getItemID(),
                    item.getitemName(),
                    item.getQuantity(),
                    item.getidShoppingList(),
                    item.getcategoryID()
            );
            // Save the itemEntity
            return mapToItem(repo.save(itemEntity));
        } else {
            // Handle the case where categoryID is null
            throw new RuntimeException("CategoryID is required for saving an item." + item.getcategoryID().getCategoryID());
        }
    }


    private ItemEntity mapToItem(ItemEntity itemEntity) {
        return new ItemEntity(itemEntity.getItemID(), itemEntity.getitemName(), itemEntity.getQuantity(), itemEntity.getidShoppingList(), itemEntity.getcategoryID());
    }


    public ItemEntity update(Long id, ItemEntity updatedItem) {
        ItemEntity existingItem = repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

        if (updatedItem.getitemName() != null) {
            existingItem.setitemName(updatedItem.getitemName());
        }
        if (updatedItem.getQuantity() != 0) {
            existingItem.setQuantity(updatedItem.getQuantity());
        }
        if (updatedItem.getcategoryID() != null) {
            existingItem.setcategoryID(updatedItem.getcategoryID());
        }
        if (updatedItem.isDone() != null) {
            existingItem.setDone(updatedItem.isDone());
        }
        return repo.save(existingItem);
    }


    /*
    public ItemEntity update(Long id, ItemEntity updatedItem) {
        ItemEntity existingItem = repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

        if (updatedItem.getitemName() != null) {
            existingItem.setitemName(updatedItem.getitemName());
        }
        if (updatedItem.getQuantity() != 0) {
            existingItem.setQuantity(updatedItem.getQuantity());
        }
        if (updatedItem.getcategoryID() != null) {
            existingItem.setcategoryID(updatedItem.getcategoryID());
        }
        if (updatedItem.isDone() != null) {
            existingItem.setDone(updatedItem.isDone());

            // Remove the item from the associated shopping list
            ShoppingList shoppingList = existingItem.getidShoppingList();
            if (shoppingList != null) {
                shoppingList.getItems().remove(existingItem);
            }

            repo.delete(existingItem);
            return null;

        }
        return repo.save(existingItem);
    }

     */







/*
    public void delete(Long id) {
        repo.deleteById(id);
    }

 */


    @Transactional
    public void delete(Long itemId) {
        ItemEntity item = repo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Remove the item from the associated shopping list
        ShoppingList shoppingList = item.getidShoppingList();
        if (shoppingList != null) {
            shoppingList.getItems().remove(item);
        }

        repo.delete(item);
    }

    /*
    public void deleteAllItems() {
        repo.deleteAll();
    }

     */


    public void deleteAllItems() {
        List<ItemEntity> allItems = repo.findAll();
        ShoppingList shoppingList;

        for (ItemEntity itemEntity : allItems) {
            shoppingList = itemEntity.getidShoppingList();

            if (shoppingList != null) {
                shoppingList.getItems().remove(itemEntity);
            }
        }
        repo.deleteAll();
    }


}
