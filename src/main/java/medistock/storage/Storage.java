package medistock.storage;

import medistock.exception.MediStockException;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import medistock.inventory.InventoryItem;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.readAllLines;

public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves newly created Item to data.txt
     *
     * @param data the data to be saved
     * @throws IOException if an Error occurs while reading the file
     */
    public void saveToFile(Storable data) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile(), true);
        fw.write(data.toFileFormat() + System.lineSeparator());
        fw.close();
    }

    public void readFromFile(Inventory inventory, Storable data) throws IOException, MediStockException {
        List<String> lines = readAllLines(filePath);
        String itemName = "";
        for (String line : lines) {
            if ((line.startsWith("Item: ")) || (!itemName.equals(getItemName(line).trim()))) {
                itemName = getItemName(line).trim();
                inventory.addItem(parseInventoryItem(itemName));
            } else if (line.startsWith("Batch")) {

            }

        }
    }

    public String isSameItem(String itemName)

    public String getItemName(String line) {
        String[] name = splitLine(line);
        return name[1];
    }

    public String[] splitLine(String line) {
        return line.split("[|:]");
    }

    public InventoryItem parseInventoryItem(String line) throws MediStockException {
        String regex = "Item: (.*?) \\((.*?)\\) \\| (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        String name = matcher.group(1);
        String unit = matcher.group(2);
        int minThreshold = Integer.parseInt(matcher.group(3));
        return new InventoryItem(name, unit, minThreshold);
    }

    public InventoryItem parseItemBatch(String line) throws MediStockException {
        String regex = "Batch (.*?) \\((.*?)\\) \\| (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        String name = matcher.group(1);
        String unit = matcher.group(2);
        int minThreshold = Integer.parseInt(matcher.group(3));
        return new InventoryItem(name, unit, minThreshold);
    }


    //TODO delete, withdraw

    public void createNewFile() {
        try {
            Path parentDir = filePath.getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeInventory(Inventory inventory) throws IOException {
        if (exists(filePath)) {

            readFromFile(inventory, Storable);
        } else {
            createNewFile();
        }
    }
}





