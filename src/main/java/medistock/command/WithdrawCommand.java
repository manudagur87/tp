package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

public class WithdrawCommand extends String {
    private final java.lang.String name;
    private final int quantity;

    public WithdrawCommand(java.lang.String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, List<java.lang.String> histories) throws MediStockException {
        InventoryItem item = inventory.getItem(name);
        item.withdraw(quantity);
        ui.printWithdraw(quantity, item);
        histories.add(toHistoryString(item.getUnit()));
    }

    public java.lang.String toHistoryString(java.lang.String unit) {
        return "Withdrawn " + quantity + " " + unit + " of '" + name + "'.";
    }
}
