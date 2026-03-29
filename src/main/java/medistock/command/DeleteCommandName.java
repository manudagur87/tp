package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

public class DeleteCommandName extends String {
    private final java.lang.String name;

    public DeleteCommandName(java.lang.String name) {
        this.name = name;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, List<java.lang.String> histories) throws MediStockException {
        InventoryItem deletedItem = inventory.deleteItem(name);
        ui.printDelete(deletedItem);
        histories.add(toHistoryString());
    }

    public java.lang.String toHistoryString() {
        return "Deleted '" + name + "'.";
    }
}
