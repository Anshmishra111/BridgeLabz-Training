package BridgeLab_Training.Jdbc.util;

import BridgeLab_Training.Jdbc.config.DBConnection;

import java.sql.*;

public class DatabaseBackupUtil {

    public static void validateSchema() {

        try (Connection conn = DBConnection.getConnection()) {

            DatabaseMetaData meta = conn.getMetaData();

            ResultSet tables =
                    meta.getTables(null, null, "%", new String[]{"TABLE"});

            System.out.println("Backing up tables:");
            while (tables.next()) {
                System.out.println(" - " + tables.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Backup validation failed", e);
        }
    }
}