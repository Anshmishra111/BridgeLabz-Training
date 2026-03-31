package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditLogDAOImpl implements AuditLogDAO {

    @Override
    public List<String> getAuditLogs(String table, String user) {

        String sql = """
            SELECT table_name, operation, record_id, changed_at, changed_by
            FROM audit_log
            WHERE table_name=? OR changed_by=?
            ORDER BY changed_at DESC
        """;

        List<String> logs = new ArrayList<>();

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, table);
            ps.setString(2, user);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logs.add(
                    rs.getString(1) + " | " +
                    rs.getString(2) + " | " +
                    rs.getInt(3) + " | " +
                    rs.getTimestamp(4) + " | " +
                    rs.getString(5)
                );
            }
            return logs;

        } catch (SQLException e) {
            throw new RuntimeException("Error reading audit logs", e);
        }
    }
}