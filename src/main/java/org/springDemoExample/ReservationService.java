package org.springDemoExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ReservationService {

    public void makeReservation(int userId, int trainNumber, String classType, String dateOfJourney, String from, String to) {
        String pnr = generatePnr();
        String query = "INSERT INTO reservations (pnr, user_id, train_number, class_type, date_of_journey, from_station, to_station) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pnr);
            stmt.setInt(2, userId);
            stmt.setInt(3, trainNumber);
            stmt.setString(4, classType);
            stmt.setString(5, dateOfJourney);
            stmt.setString(6, from);
            stmt.setString(7, to);
            stmt.executeUpdate();
            System.out.println("Reservation successful! PNR: " + pnr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generatePnr() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    public String getTrainName(int trainNumber) {
        String query = "SELECT train_name FROM trains WHERE train_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trainNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("train_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}