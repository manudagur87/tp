package medistock.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.ui.Ui;
import org.junit.jupiter.api.Test;

public class CreateCommandTest {


    @Test
    void execute_validCreate_completesAndAddsItem() {
        Inventory inventory = new Inventory();
        Ui ui = new Ui();
        List<String> histories = new ArrayList<>();
        CreateCommand command = new CreateCommand("Aspirin", "Tablets", 10);

        assertDoesNotThrow(() -> command.execute(inventory, ui, histories));

        assertEquals(1, inventory.getSize());
        assertTrue(inventory.hasItem("Aspirin"));
    }

    @Test
    void execute_validCreate_addsOneHistoryEntry() throws MediStockException {
        Inventory inventory = new Inventory();
        Ui ui = new Ui();
        List<String> histories = new ArrayList<>();
        CreateCommand command = new CreateCommand("Aspirin", "Tablets", 10);

        command.execute(inventory, ui, histories);

        assertEquals(1, histories.size());
        assertEquals("Created 'Aspirin' of 'Tablets' unit with minimum threshold of 10.", histories.get(0));
    }

    @Test
    void execute_validCreate_writesSingleItemToStorage() throws MediStockException {
        Inventory inventory = new Inventory();
        Ui ui = new Ui();
        List<String> histories = new ArrayList<>();
        CreateCommand command = new CreateCommand("Aspirin", "Tablets", 10);

        command.execute(inventory, ui, histories);
    }

    @Test
    void execute_duplicateItem_throwsException() throws MediStockException {
        Inventory inventory = new Inventory();
        Ui ui = new Ui();
        List<String> histories = new ArrayList<>();
        CreateCommand firstCommand = new CreateCommand("Aspirin", "Tablets", 10);
        CreateCommand secondCommand = new CreateCommand("Aspirin", "Tablets", 10);

        firstCommand.execute(inventory, ui, histories);

        assertThrows(MediStockException.class,
                () -> secondCommand.execute(inventory, ui, histories));
        assertEquals(1, inventory.getSize());
        assertEquals(1, histories.size());
    }
}
