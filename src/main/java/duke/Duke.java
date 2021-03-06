package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.common.Messages;
import duke.parser.Parser;
import duke.ui.Ui;

public class Duke {

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        greetUser();
        Scanner scanner = new Scanner(System.in);
        Parser.handleCommand(scanner);
    }

    private static void greetUser() {
        List<String> messages = new ArrayList<>();
        messages.add(Messages.HELLO_MESSAGE);
        messages.add(Messages.ASSIST_MESSAGE);
        Ui.reply(messages);
    }
}