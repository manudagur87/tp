package medistock.inventory;

import medistock.exception.MediStockException;
import medistock.ui.Ui;
import org.junit.jupiter.api.Test;

import medistock.command.BatchCommand;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BatchTest {

    @Test
    void execute_existingItem_increasesQuantity() throws MediStockException {
        Inventory inventory = new Inventory();
        Ui ui = new Ui();
        List<String> histories = new ArrayList<>();

        InventoryItem existingItem = new InventoryItem("Aspirin 500mg", "Tablets", 200);
        inventory.addItem(existingItem);

        LocalDate date = LocalDate.parse("2028-06-07");
        BatchCommand command = new BatchCommand("Aspirin 500mg", 50, date);

        command.execute(inventory, ui, histories);

        InventoryItem updatedItem = inventory.getItem("Aspirin 500mg");
        assertEquals(50, updatedItem.getQuantity());
    }

    @Test
    void execute_missingItem_throwsException() {
        Inventory inventory = new Inventory();
        Ui ui = new Ui();
        List<String> histories = new ArrayList<>();

        LocalDate date = LocalDate.parse("2028-06-07");
        BatchCommand command = new BatchCommand("Aspirin 500mg", 50, date);

        assertThrows(MediStockException.class,
                () -> command.execute(inventory, ui, histories));
    }
}
