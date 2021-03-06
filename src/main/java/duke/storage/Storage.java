package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.common.Messages;
import duke.data.Task;
import duke.ui.Ui;

public class Storage {
    private static final String filePath = "./tasks.txt";

    /**
     * Saves all tasks in memory to local text file.
     * File found at file path.
     * @param tasks Tasks in memory.
     */
    public static void save(List<Task> tasks) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                String textInput = TaskEncoder.convertToFile(task);
                if (textInput != null) {
                    fw.write(textInput + "\n");
                }
            }
            fw.close();
        } catch (IOException | NullPointerException exception) {
            Ui.notifyError(Messages.CORRUPTED_SAVE_FILE_MESSAGE);
        }
    }

    /**
     * Reads all tasks in local text file into Duke memory.
     * @return List of tasks from text file specified in file path.
     */
    public static List<Task> read() {
        List<String> messages = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                messages.add("Tasks can be found at: " + f.getPath());
                Ui.reply(messages);
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String input = s.nextLine();
                if (!input.isEmpty()) {
                    Task task = TaskDecoder.convertToTask(input);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        } catch (IOException | NullPointerException exception) {
            Ui.notifyError(Messages.CORRUPTED_SAVE_FILE_MESSAGE);
        }
        return tasks;
    }
}
