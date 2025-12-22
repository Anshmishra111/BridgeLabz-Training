// package core-java-practice.gcr-codebase.string;
    public class NullPointerExceptionDemo {
    public static void generateException() {
        String text = null;   
        System.out.println(text.length());
    }
    public static void handleException() {
        String text = null;

        try {
            System.out.println(text.length());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught and handled");
        }
    }

    public static void main(String[] args) {
        System.out.println("Calling method to generate exception:");
        generateException();
        System.out.println("\nCalling method to handle exception:");
        handleException();
    }
}

    

