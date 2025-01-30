import java.util.Scanner;

class UI {

    private final Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Hello! I'm TalkGPT.\nWhat can I do for you?");
    }

    public void end() {
        System.out.println("Goodbye! See you next time!");
    }

    public String getUserInput() {
        return sc.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh...");
    }

    public void showFormattedMessage(Messages.Info message, Object... args) {
        System.out.printf(message.get() + "%n", args);
    }

    public void emptyLine() {
        System.out.println();
    }
}