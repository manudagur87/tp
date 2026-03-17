package medistock.inventory;

import medistock.exception.MediStockException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InventoryTest {

    @Test
    void addItem_duplicateName_throwsException() throws MediStockException {
        Inventory inventory = new Inventory();

        InventoryItem firstItem = new InventoryItem("Aspirin 500mg", "Tablet", 200);
        InventoryItem duplicateItem = new InventoryItem("Aspirin 500mg", "Tablet", 200);

        inventory.addItem(firstItem);
        assertThrows(MediStockException.class,
                () -> inventory.addItem(duplicateItem));
    }

    @Test
    void addItem_validItem_sizeIncreases() throws MediStockException {
        Inventory inventory = new Inventory();
        int initialInventorySize = inventory.getSize();

        InventoryItem validItem = new InventoryItem("Aspirin 500mg", "Tablet", 200);
        inventory.addItem(validItem);
        assertEquals(initialInventorySize + 1, inventory.getSize());
    }

    @Test
    void hasItem() {
    }

    @Test
    void getItem() {
    }

    @Test
    void getSize() {
    }

    @Test
    void deleteItem_existingItem_removesItem() throws MediStockException {
        Inventory inventory = new Inventory();

        String name = "Aspirin 500mg";
        InventoryItem existingItem = new InventoryItem(name, "Tablet", 200);
        inventory.addItem(existingItem);
        inventory.deleteItem(name);
        assertEquals(0, inventory.getSize());
    }

    @Test
    void deleteItem_nonExistingItem_throwsException() {
        Inventory inventory = new Inventory();

        assertThrows(MediStockException.class,
                () -> inventory.deleteItem("Panadol"));
    }
}
