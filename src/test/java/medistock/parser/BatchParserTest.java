package medistock.parser;

import medistock.exception.MediStockException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BatchParserTest {

    @Test
    void prepareBatch_missingNameTag_throwsException() {
        String input = "batch q/10 d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_incorrectTagOrder_throwsException() {
        String input = "batch q/10 n/Vyvanse 40mg d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_quantityNegative_throwsException() {
        String input = "batch n/Aspirin q/-5 d/2025-01-01";

        assertThrows(MediStockException.class,
                () -> Parser.prepareBatch(input));
    }

    @Test
    void prepareBatch_validFormat_doesNotThrow() {
        String input = "batch n/Aspirin q/10 d/2025-01-01";

        assertDoesNotThrow(() -> Parser.prepareBatch(input));
    }
}
