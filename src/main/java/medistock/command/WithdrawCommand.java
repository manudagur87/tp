package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

/**
 * Command to withdraw a specified quantity of an inventory item.
 */
public class WithdrawCommand extends Command {
    private final String name;
    private final int quantity;

    public WithdrawCommand(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, List<String> histories) throws MediStockException {
        InventoryItem item = inventory.getItem(name);
        int withdrawnQuantity = item.withdraw(quantity);
        ui.printWithdraw(quantity, withdrawnQuantity, item);
        histories.add(toHistoryString(withdrawnQuantity, item.getUnit()));
    }

    public String toHistoryString(int withdrawnQuantity, String unit) {
        return "Withdrawn " + withdrawnQuantity + " " + unit + " of '" + name + "'.";
    }
}
