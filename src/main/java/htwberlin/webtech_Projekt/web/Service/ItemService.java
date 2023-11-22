package htwberlin.webtech_Projekt.web.Service;

import htwberlin.webtech_Projekt.web.Entities.CategoryEntity;
import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import htwberlin.webtech_Projekt.web.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository repo;
    private final CategoryService categoryService;  // Inject CategoryService

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

    public ItemEntity save(ItemEntity item) {
        ItemEntity itemEntity = new ItemEntity(item.getItemID(), item.getitemName(), item.getQuantity()/*, item.getShopid()*/, item.getcategoryID());
        return mapToItem(repo.save(itemEntity));
    }


/*
    public ItemEntity save(ItemEntity item) {
        // Fetch the CategoryEntity from the database based on the ID provided in the input item
        CategoryEntity categoryEntity = item.getcategoryID() != null ?
                categoryService.findById(item.getcategoryID().getCategoryID()) : null;

        // Create a new ItemEntity with the fetched CategoryEntity
        ItemEntity itemEntity = new ItemEntity(
                item.getItemID(),
                item.getitemName(),
                item.getQuantity(),
                //item.getShopid(),
                item.getcategoryID()
                //categoryEntity
        );

        return mapToItem(repo.save(itemEntity));
    }

 */


/*
    public ItemEntity save(ItemEntity item) {
        // Check if the item has a categoryID
        if (item.getcategoryID() != null) {
            // Fetch the CategoryEntity from the database based on the ID provided in the input item
            CategoryEntity categoryEntity = categoryService.findById(item.getcategoryID().getCategoryID());

            // Check if the fetched category exists
            if (categoryEntity == null) {
                // Handle the case where the category doesn't exist (e.g., throw an exception or return an error response)
                throw new RuntimeException("Category not found for ID: " + item.getcategoryID().getCategoryID());
            }

            // Create a new ItemEntity with the fetched CategoryEntity
            ItemEntity itemEntity = new ItemEntity(
                    item.getItemID(),
                    item.getitemName(),
                    item.getQuantity(),
                    //item.getShopid(),
                    categoryEntity
            );

            // Save the itemEntity
            return mapToItem(repo.save(itemEntity));
        } else {
            // Handle the case where categoryID is null (e.g., throw an exception or return an error response)
            throw new RuntimeException("CategoryID is required for saving an item.");
        }
    }


 */
/*
    public ItemEntity save(ItemEntity item) {
        // Check if the item has a categoryID
        if (item.getcategoryID() != null && item.getcategoryID().getCategoryID() != null) {
            // Fetch the CategoryEntity from the database based on the ID provided in the input item
            CategoryEntity categoryEntity = categoryService.findById(item.getcategoryID().getCategoryID());

            // Check if the fetched category exists
            if (categoryEntity == null) {
                // Handle the case where the category doesn't exist (e.g., throw an exception or return an error response)
                throw new RuntimeException("Category not found for ID: " + item.getcategoryID().getCategoryID());
            }

            // Set the fetched CategoryEntity to the item
            item.setcategoryID(categoryEntity);

            // Save the item
            return mapToItem(repo.save(item));
        } else {
            // Handle the case where categoryID is null or not provided (e.g., throw an exception or return an error response)
            throw new RuntimeException("CategoryID is required for saving an item.");
        }
    }

 */

    private ItemEntity mapToItem(ItemEntity itemEntity) {
        return new ItemEntity(itemEntity.getItemID(), itemEntity.getitemName(), itemEntity.getQuantity()/*, itemEntity.getShopid()*/, itemEntity.getcategoryID());
    }



    public ItemEntity update(Long id, ItemEntity updatedItem) {
        ItemEntity existingItem = repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

        existingItem.setitemName(updatedItem.getitemName());
        existingItem.setQuantity(updatedItem.getQuantity());
        //existingItem.setShopid(updatedItem.getShopid());
        existingItem.setcategoryID(updatedItem.getcategoryID());

        return repo.save(existingItem);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
