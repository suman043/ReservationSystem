package org.springDemoExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CancellationService {

    public void cancelReservation(String pnr) {
        String query = "DELETE FROM reservations WHERE pnr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pnr);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cancellation successful for PNR: " + pnr);
            } else {
                System.out.println("PNR not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showReservationDetails(String pnr) {
        String query = "SELECT * FROM reservations WHERE pnr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pnr);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("PNR: " + rs.getString("pnr"));
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Train Number: " + rs.getInt("train_number"));
                System.out.println("Class: " + rs.getString("class_type"));
                System.out.println("Date of Journey: " + rs.getDate("date_of_journey"));
                System.out.println("From: " + rs.getString("from_station"));
                System.out.println("To: " + rs.getString("to_station"));
            } else {
                System.out.println("PNR not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}