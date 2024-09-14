package org.springDemoExample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        ReservationService reservationService = new ReservationService();
        CancellationService cancellationService = new CancellationService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Reservation System");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (loginService.authenticate(username, password)) {
            System.out.println("Login successful!");

            // Reservation Module
            System.out.println("Enter Train Number: ");
            int trainNumber = scanner.nextInt();
            System.out.println("Train Name: " + reservationService.getTrainName(trainNumber));
            // Fill remaining reservation details and make a reservation

            // Cancellation Module
            System.out.println("Enter PNR to cancel: ");
            String pnr = scanner.next();
            cancellationService.showReservationDetails(pnr);
            System.out.print("Do you want to cancel? (yes/no): ");
            String confirmation = scanner.next();
            if (confirmation.equalsIgnoreCase("yes")) {
                cancellationService.cancelReservation(pnr);
            }

        } else {
            System.out.println("Invalid login credentials!");
        }
        scanner.close();
    }
}