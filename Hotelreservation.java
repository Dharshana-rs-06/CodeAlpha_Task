

import java.util.*;

class Room {
    int roomNo;
    String category;
    boolean filled;
    String customer;

    Room(int roomNo, String category) {
        this.roomNo = roomNo;
        this.category = category;
        this.filled = false;
        this.customer = "";
    }

    public void book(String customer) {
        this.customer = customer;
        this.filled = true;
    }

    public void cancel() {
        this.filled = false;
        this.customer = "";
    }

    public void viewBooking() {
        if (filled) {
            System.out.println("*** Customer Details ***");
            System.out.println("Customer Name: " + customer);
            System.out.println("Room Booked: " + roomNo);
        } else {
            System.out.println("This room is not currently booked.");
        }
    }

    toString() {
        return "Room No: " + roomNo + " (" + category + ") - " + (filled ? "Booked" : "Available");
    }
}

public class Hotelreservation {
    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new Room(109, "Deluxe"));
        rooms.add(new Room(108, "Deluxe"));
        rooms.add(new Room(103, "Standard"));
        rooms.add(new Room(101, "Deluxe"));
        rooms.add(new Room(100, "Suite"));
        rooms.add(new Room(111, "Standard"));
        rooms.add(new Room(112, "Suite"));
        rooms.add(new Room(113, "Deluxe"));

        int choice;
        do {
            System.out.println("\nWelcome To RS Hotels");
            System.out.println("Kindly enter your service needed:");
            System.out.println("1: View Available Rooms");
            System.out.println("2: Book a Room");
            System.out.println("3: View Recent Booking");
            System.out.println("4: Cancel Booking");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    roomsAvailable();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    viewBooking();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    System.out.println("Thank you for using RS Hotel Services.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    public static void roomsAvailable() {
        System.out.println("\nRooms Available:");
        for (Room r : rooms) {
            if (!r.filled) {
                System.out.println(r);
            }
        }
    }

    public static void bookRoom() {
        System.out.print("Enter the room number you want to book: ");
        int rr = sc.nextInt();
        for (Room r : rooms) {
            if (r.roomNo == rr) {
                if (!r.filled) {
                    System.out.print("Enter your name: ");
                    sc.nextLine(); 
                    String cn = sc.nextLine();
                    r.book(cn);
                    double payment = paymentDetails(r.category);
                    System.out.println("Payment done: $" + payment);
                    System.out.println("Room booked successfully!");
                    return;
                } else {
                    System.out.println("Room already filled.");
                    return;
                }
            }
        }
        System.out.println("Room not found.");
    }

    public static void viewBooking() {
        System.out.print("Enter your room number to view booking: ");
        int rn = sc.nextInt();
        for (Room r : rooms) {
            if (r.roomNo == rn) {
                r.viewBooking();
                return;
            }
        }
        System.out.println("Room not found.");
    }

    public static void cancelBooking() {
        System.out.print("Enter your room number to cancel booking: ");
        int rn = sc.nextInt();
        for (Room r : rooms) {
            if (r.roomNo == rn) {
                if (r.filled) {
                    r.cancel();
                    System.out.println("Booking cancelled.");
                } else {
                    System.out.println("This room is not booked.");
                }
                return;
            }
        }
        System.out.println("Room not found.");
    }

    public static double paymentDetails(String category) {
        switch (category) {
            case "Standard":
                return 1000.78;
            case "Deluxe":
                return 2000.65;
            case "Suite":
                return 2500.45;
            default:
                System.out.println("Invalid room category.");
                return 0;
        }
    }
}
