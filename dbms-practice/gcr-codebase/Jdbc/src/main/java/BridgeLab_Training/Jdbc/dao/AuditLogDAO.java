package BridgeLab_Training.Jdbc.dao;

import java.util.List;

public interface AuditLogDAO {

    List<String> getAuditLogs(String table, String user);
}