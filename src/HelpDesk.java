import java.util.Scanner;

public class HelpDesk {
    public static void main(String[] args) {
        QueueManager queueManager = QueueManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPag-ibig Centralized Queuing System");
            System.out.println("1. Get Next Queue Number");
            System.out.println("2. View Current Queue Number (Online Monitoring)");
            System.out.println("3. Reset Queue Number");
            System.out.println("4. Show Desk Display");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(queueManager.getNextQueueNumberFormatted());
                    break;
                case 2:
                    System.out.println("Current queue number: " + queueManager.getCurrentQueueNumberFormatted());
                    break;
                case 3:
                    System.out.print("Enter new queue number: ");
                    int newNum = scanner.nextInt();
                    System.out.println(queueManager.resetQueueNumberFormatted(newNum));
                    break;
                case 4:
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Desk " + (i + 1) + ": Now Serving " + queueManager.getDeskServingNumberFormatted(i));
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
