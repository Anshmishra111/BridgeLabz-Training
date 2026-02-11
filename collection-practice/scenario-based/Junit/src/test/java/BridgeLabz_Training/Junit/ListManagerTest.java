package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
public class ListManagerTest {
ListManager manager=new ListManager();
@Test
void testAddElement() {
	List<Integer> list=new ArrayList<>();
	manager.addElement(list,10);
	assertTrue(list.contains(10));
	assertEquals(1,manager.getSize(list));
	
}
@Test
void testRemoveElement() {
	List<Integer> list=new ArrayList<>();
	list.add(20);
	manager.removeElement(list,20);
	 assertFalse(list.contains(20));
     assertEquals(0, manager.getSize(list));
 }

 @Test
 void testGetSize() {
     List<Integer> list = new ArrayList<>();
     list.add(1);
     list.add(2);

     assertEquals(2, manager.getSize(list));
 
}
}
