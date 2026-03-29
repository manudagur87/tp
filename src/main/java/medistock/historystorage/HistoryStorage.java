package medistock.historystorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class HistoryStorage {
    private final String filePath;
    private final File file;

    /**
     * Represents the storage of the history of commands.
     * @param filePath A relative path to the text file that stores the history of commands.
     */
    public HistoryStorage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Returns the history of commands in the form of List by loading the text in the text file.
     *
     * @param commands New History List.
     * @return Loaded History List.
     * @throws FileNotFoundException If the input text file does not exist.
     */
    public List<String> load(List<String> commands) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return commands;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String cmd;

            while ((cmd = br.readLine()) != null) {
                commands.add(cmd);
            }
        }

        return commands;
    }

    /**
     * Writes the updated History List into the text file. Creates a new text file if it does not exist.
     *
     * @param commands History of commands.
     * @throws FileNotFoundException If error writing text file.
     */
    public void save(List<String> commands) throws IOException {
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("Created a new file at '" + filePath + "' to store history.");
            }
        }
        try (FileWriter fw = new FileWriter(filePath)) {
            for (String commandText : commands) {
                fw.write(commandText + System.lineSeparator());
            }
        }
    }
}
