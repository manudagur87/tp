package medistock.storage;

import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }
    /**
     * Saves newly created Item to data.txt
     * @param item the InventoryItem, to be saved
     * @throws IOException if an Error occurs while reading the file
     */

    public void saveItem(Storable data) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile(), true);
        fw.write(data.toFileFormat());
        fw.close();
    }
}




