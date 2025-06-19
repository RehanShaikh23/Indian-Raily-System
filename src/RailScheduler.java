import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RailScheduler {

    public static void displayTicketBookingTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\n=== TICKET BOOKING TIME ===");
        System.out.println("Ticket booking date and time: " + now.format(formatter));
        System.out.println("============================\n");
    }

    public static void displayCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");

        System.out.println("\n=== TODAY'S DATE INFO ===");
        System.out.println("Month: " + today.format(monthFormatter));
        System.out.println("Day: " + today.format(dayFormatter));
        System.out.println("Today's date: " + today);
        System.out.println("=========================\n");
    }

    public static LocalDateTime getUserInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter the date and time (YYYY-MM-DD HH:MM): ");
            String userInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                return LocalDateTime.parse(userInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid format. Please try again using format YYYY-MM-DD HH:MM");
                System.out.println("   Example: 2025-06-20 14:30");
            }
        }
    }

    public static void handleTrainTimetable(Scanner scanner) {
        System.out.println("\n=== SPECIAL TRAIN TIMETABLE ===");

        System.out.println("🚆 Train departure point time detail:");
        LocalDateTime departure = getUserInput(scanner);
        System.out.println("✅ Train start date and time: " + departure);

        Duration untilDeparture = Duration.between(LocalDateTime.now(), departure);
        if (untilDeparture.isNegative()) {
            System.out.println("⚠️  The departure time has already passed.");
        } else {
            long hours = untilDeparture.toHours();
            long minutes = untilDeparture.toMinutes() % 60;
            System.out.println("⏰ Time remaining: " + hours + " hours and " + minutes + " minutes");
        }

        System.out.println("\n🏁 Train arrival point time detail:");
        LocalDateTime arrival = getUserInput(scanner);
        System.out.println("✅ Train reaching date: " + arrival);

        if (arrival.isBefore(departure)) {
            System.out.println("❌ Error: Arrival time cannot be before departure time.");
            System.out.println("===============================\n");
            return;
        }

        Duration travelTime = Duration.between(departure, arrival);
        long travelHours = travelTime.toHours();
        long travelMinutes = travelTime.toMinutes() % 60;

        System.out.println("🕐 Travel Time: " + travelHours + " hours and " + travelMinutes + " minutes");
        System.out.println("===============================\n");
    }

    public static void displayMenu() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     🚄 VANDE BHARAT RAIL SCHEDULER   ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Display ticket booking time     ║");
        System.out.println("║  2. Display today's date             ║");
        System.out.println("║  3. Add special train timetable      ║");
        System.out.println("║  4. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Enter your choice (1-4): ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🚄 Welcome to Vande Bharat Rail Scheduler System!");
        System.out.println("Current System Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        while (true) {
            displayMenu();

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        displayTicketBookingTime();
                        break;
                    case 2:
                        displayCurrentDate();
                        break;
                    case 3:
                        handleTrainTimetable(scanner);
                        break;
                    case 4:
                        System.out.println("\n🙏 Thank you for using Vande Bharat Rail Scheduler!");
                        System.out.println("Safe travels! 🚄");
                        scanner.close();
                        return;
                    default:
                        System.out.println("❌ Invalid choice. Please enter a number between 1-4.\n");
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Please enter a valid number.\n");
                scanner.nextLine();
            }
        }
    }
}
