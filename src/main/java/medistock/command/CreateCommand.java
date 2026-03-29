package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import medistock.ui.Ui;

public class CreateCommand extends String {
    private final java.lang.String name;
    private final java.lang.String unit;
    private final int minimumThreshold;

    public CreateCommand(java.lang.String name, java.lang.String unit, int minimumThreshold) {
        this.name = name;
        this.unit = unit;
        this.minimumThreshold = minimumThreshold;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, List<java.lang.String> histories) throws MediStockException {
        InventoryItem item = new InventoryItem(name, unit, minimumThreshold);
        inventory.addItem(item);
        ui.printCreate(name,unit,minimumThreshold);
        histories.add(toHistoryString());
    }

    public java.lang.String toHistoryString() {
        return "Created '" + name + "' of '" + unit + "' unit with minimum threshold of " + minimumThreshold + ".";
    }
}
