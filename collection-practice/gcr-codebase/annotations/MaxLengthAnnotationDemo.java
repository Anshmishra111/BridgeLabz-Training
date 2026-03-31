package annotations;
import java.lang.annotation.*;
import java.lang.reflect.Field;

// ---------- ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// ---------- USER CLASS ----------
class User {

    @MaxLength(10)
    private String username;

    public User(String username) {

        try {
            Field field = this.getClass().getDeclaredField("username");
            field.setAccessible(true);

            MaxLength max = field.getAnnotation(MaxLength.class);

            if (max != null && username.length() > max.value()) {
                throw new IllegalArgumentException(
                        "Username length exceeds " + max.value()
                );
            }

            this.username = username;

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }
}

// ---------- MAIN CLASS ----------
public class MaxLengthAnnotationDemo {

    public static void main(String[] args) {

        try {
            User u1 = new User("Ansh");
            System.out.println("Username: " + u1.getUsername());

            User u2 = new User("VeryLongUsername");
            System.out.println("Username: " + u2.getUsername());

        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}
