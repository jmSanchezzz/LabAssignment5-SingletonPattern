public class QueueManager {
    private static QueueManager instance;
    private int currentQueueNumber;
    private int[] deskServingNumbers = new int[3]; // For 3 desks
    private int nextDeskToServe = 0; // Tracks which desk gets the next queue number

    private QueueManager() {
        currentQueueNumber = 1;
        for (int i = 0; i < deskServingNumbers.length; i++) {
            deskServingNumbers[i] = 0; // Initialize to show A000
        }
    }

    public static synchronized QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }

    public synchronized String getNextQueueNumberFormatted() {
        int queueNum = getNextQueueNumber();
        // Assign to desk automatically in round-robin fashion
        deskServingNumbers[nextDeskToServe] = queueNum;
        String assignedDesk = "Desk " + (nextDeskToServe + 1);
        nextDeskToServe = (nextDeskToServe + 1) % 3;
        return String.format("Your queue number: %s (%s)", formatQueueNumber(queueNum), assignedDesk);
    }

    public synchronized int getNextQueueNumber() {
        return currentQueueNumber++;
    }

    public synchronized int getCurrentQueueNumber() {
        return currentQueueNumber;
    }

    public synchronized void resetQueueNumber(int newNumber) {
        currentQueueNumber = newNumber;
        for (int i = 0; i < deskServingNumbers.length; i++) {
            deskServingNumbers[i] = newNumber - 1; // Show previous number (e.g., A000 if reset to 1)
        }
        nextDeskToServe = 0;
    }

    public synchronized String getCurrentQueueNumberFormatted() {
        return formatQueueNumber(getCurrentQueueNumber());
    }

    public synchronized String formatQueueNumber(int number) {
        return String.format("A%03d", number);
    }

    public synchronized String getDeskServingNumberFormatted(int deskIndex) {
        if (deskIndex >= 0 && deskIndex < 3) {
            return formatQueueNumber(deskServingNumbers[deskIndex]);
        }
        return "Invalid Desk";
    }
}
