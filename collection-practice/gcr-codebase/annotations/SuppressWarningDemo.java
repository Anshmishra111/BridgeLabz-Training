package annotations;

import java.util.ArrayList;

public class SuppressWarningDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // Raw ArrayList (without generics)
        ArrayList list = new ArrayList();

        list.add("Java");
        list.add(100);   // Different data types

        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}
