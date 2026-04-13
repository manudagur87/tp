package medistock.storage;

import medistock.exception.MediStockException;
import medistock.inventory.Batch;
import medistock.inventory.Inventory;
import medistock.inventory.InventoryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @TempDir
    Path tempDir;

    private Storage storage;
    private Path testFile;

    @BeforeEach
    void setUp() {
        testFile = tempDir.resolve("test_data.txt");
        storage = new Storage(testFile);
    }

    @Test
    void parseInventoryItem_validLine_parsesCorrectly() throws MediStockException {
        InventoryItem item = storage.parseInventoryItem("Item: Paracetamol (tablet) | 50 | 3");
        assertEquals("Paracetamol", item.getName());
    }

    @Test
    void parseInventoryItem_nameWithSpaces_parsesCorrectly() throws MediStockException {
        // medicine names commonly contain spaces
        InventoryItem item = storage.parseInventoryItem("Item: Amoxicillin 500mg Capsule (capsule) | 20 | 1");
        assertEquals("Amoxicillin 500mg Capsule", item.getName());
    }

    @Test
    void parseInventoryItem_missingDelimiters_throwsException() {
        assertThrows(MediStockException.class,
                () -> storage.parseInventoryItem("Item: Paracetamol tablet 50 3"));
    }

    @Test
    void parseInventoryItem_missingBrackets_throwsException() {
        assertThrows(MediStockException.class,
                () -> storage.parseInventoryItem("Item: Paracetamol tablet | 50 | 3"));
    }

    @Test
    void parseInventoryItem_emptyLine_throwsException() {
        assertThrows(MediStockException.class,
                () -> storage.parseInventoryItem(""));
    }

    @Test
    void parseItemBatch_validLine_parsesCorrectly() throws MediStockException {
        Batch batch = storage.parseItemBatch("2 | 100 | 2025-12-31");
        assertNotNull(batch);
    }

    @Test
    void parseItemBatch_wrongDateFormat_throwsException() {
        // user types dd-MM-yyyy instead of yyyy-MM-dd
        assertThrows(MediStockException.class,
                () -> storage.parseItemBatch("1 | 50 | 31-12-2025"));
    }

    @Test
    void parseItemBatch_nonNumericQuantity_throwsException() {
        assertThrows(MediStockException.class,
                () -> storage.parseItemBatch("1 | lots | 2025-12-31"));
    }

    @Test
    void parseItemBatch_missingDateField_throwsException() {
        assertThrows(MediStockException.class,
                () -> storage.parseItemBatch("1 | 50"));
    }

    @Test
    void parseItemBatch_pastExpiryDate_parsesCorrectly() throws MediStockException {
        // expired batches are valid to store
        Batch batch = storage.parseItemBatch("1 | 10 | 2020-01-01");
        assertNotNull(batch);
    }

    @Test
    void saveAndLoad_singleItemNoBatches_roundTripSucceeds() throws IOException, MediStockException {
        Inventory original = new Inventory();
        original.addItem(new InventoryItem("Aspirin", "tablet", 10));
        storage.saveToFile(original);

        Inventory loaded = new Inventory();
        storage.readFromFile(loaded);

        Collection<InventoryItem> items = loaded.getAllItems();
        assertEquals(1, items.size());
        assertEquals("Aspirin", items.iterator().next().getName());
    }

    @Test
    void saveAndLoad_multipleItemsWithBatches_roundTripSucceeds() throws IOException, MediStockException {
        Inventory original = new Inventory();
        original.addItem(new InventoryItem("Paracetamol", "tablet", 50));
        original.addItem(new InventoryItem("Ibuprofen", "capsule", 30));
        original.addBatchToItem("Paracetamol", new Batch(1, 100, LocalDate.of(2026, 6, 30)));
        original.addBatchToItem("Paracetamol", new Batch(2, 50, LocalDate.of(2024, 1, 1)));
        original.addBatchToItem("Ibuprofen", new Batch(1, 200, LocalDate.of(2027, 3, 15)));
        storage.saveToFile(original);

        Inventory loaded = new Inventory();
        storage.readFromFile(loaded);

        assertEquals(2, loaded.getAllItems().size());
    }

    @Test
    void saveAndLoad_emptyInventory_loadsEmpty() throws IOException, MediStockException {
        storage.saveToFile(new Inventory());

        Inventory loaded = new Inventory();
        storage.readFromFile(loaded);

        assertTrue(loaded.getAllItems().isEmpty());
    }

    @Test
    void readFromFile_batchBeforeAnyItem_throwsException() throws IOException {
        // batch line appears before any Item: declaration
        Files.writeString(testFile, "1 | 100 | 2025-12-31\n");

        assertThrows(MediStockException.class,
                () -> storage.readFromFile(new Inventory()));
    }

    @Test
    void readFromFile_duplicateItems_throwsException() throws IOException {
        String content = "Item: Aspirin (tablet) | 10 | 1\n\nItem: Aspirin (tablet) | 10 | 2\n\n";
        Files.writeString(testFile, content);

        assertThrows(MediStockException.class,
                () -> storage.readFromFile(new Inventory()));
    }

    @Test
    void readFromFile_malformedItemLine_throwsException() throws IOException {
        Files.writeString(testFile, "Item: this line is completely wrong\n");

        assertThrows(MediStockException.class,
                () -> storage.readFromFile(new Inventory()));
    }

    @Test
    void readFromFile_malformedBatchUnderValidItem_throwsException() throws IOException {
        String content = "Item: Aspirin (tablet) | 10 | 1\nnot-a-valid-batch-line\n";
        Files.writeString(testFile, content);

        assertThrows(MediStockException.class,
                () -> storage.readFromFile(new Inventory()));
    }

    @Test
    void readFromFile_blankLinesAndBatchHeader_areIgnored() throws IOException, MediStockException {
        String content = "Item: Paracetamol (tablet) | 50 | 2\n[Batches]\n1 | 100 | 2026-06-30\n\n";
        Files.writeString(testFile, content);

        Inventory loaded = new Inventory();
        storage.readFromFile(loaded);

        assertEquals(1, loaded.getAllItems().size());
    }

    @Test
    void initializeInventory_fileDoesNotExist_createsFile() throws IOException, MediStockException {
        storage.initializeInventory(new Inventory());
        assertTrue(Files.exists(testFile));
    }

    @Test
    void initializeInventory_fileExists_loadsContent() throws IOException, MediStockException {
        Files.writeString(testFile, "Item: Aspirin (tablet) | 10 | 1\n\n");

        Inventory loaded = new Inventory();
        storage.initializeInventory(loaded);

        assertEquals(1, loaded.getAllItems().size());
    }

    @Test
    void saveAndLoad_allBatchesRemoved_nextBatchNumberNotReset() throws IOException, MediStockException {
        // nextBatchNumber=4 means batches #1–#3 were added then withdrawn; must survive round-trip
        Inventory original = new Inventory();
        original.addItem(new InventoryItem("Aspirin", "tablet", 20, 4));
        storage.saveToFile(original);

        Inventory reloaded = new Inventory();
        storage.readFromFile(reloaded);

        assertEquals(4, reloaded.getItem("Aspirin").getNextBatchNumber());
    }

    @Test
    void saveAndLoad_abortedBatchDoesNotIncrementPersistedId() throws IOException, MediStockException {
        // Batch #1 confirmed, Batch #2 aborted → nextBatchNumber must stay 2, not 3
        InventoryItem item = new InventoryItem("Aspirin", "mg", 20, 2);
        item.addBatch(new Batch(1, 100, LocalDate.of(2026, 12, 31)));

        Inventory original = new Inventory();
        original.addItem(item);
        storage.saveToFile(original);

        Inventory reloaded = new Inventory();
        storage.readFromFile(reloaded);

        InventoryItem reloadedItem = reloaded.getItem("Aspirin");
        assertEquals(2, reloadedItem.getNextBatchNumber());
        assertEquals(1, reloadedItem.getActiveBatches().get(0).getBatchNumber());
    }
}