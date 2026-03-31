package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class DatabaseConnectionTest {
DatabaseConnection db;
@BeforeEach
void setUp() {
	db=new DatabaseConnection();
	db.connect();
}
@AfterEach
void tearDown() {
	db.disconnect();
}
@Test
void testDatabaseConnected() {
	assertTrue(db.isConnected());
}
@Test
void testDatabaseDisconnectedAfterTest() {
	assertTrue(db.isConnected());

}
}
