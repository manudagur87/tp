package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

public class DeleteCommandIndex extends String {
    private final int index;

    public DeleteCommandIndex(int index) {
        this.index = index;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, List<java.lang.String> histories) throws MediStockException {
        InventoryItem deletedItem = inventory.deleteItem(index);
        ui.printDelete(deletedItem);
        histories.add(toHistoryString(deletedItem.getName()));
    }

    public java.lang.String toHistoryString(java.lang.String name) {
        return "Deleted '" + name + "'.";
    }
}
