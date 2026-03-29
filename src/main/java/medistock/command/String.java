package medistock.command;

import java.util.List;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.ui.Ui;

public abstract class String {

    public abstract void execute(Inventory inventory, Ui ui, List<java.lang.String> histories) throws MediStockException;

    public boolean isExit() {
        return false;
    };
}
