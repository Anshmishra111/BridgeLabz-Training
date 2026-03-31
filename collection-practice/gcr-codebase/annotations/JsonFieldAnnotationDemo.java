package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// ---------- CUSTOM ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

// ---------- USER CLASS ----------
class Users {

    @JsonField(name = "user_name")
    private String username;

    @JsonField(name = "user_age")
    private int age;

    private String password; // not serialized

    public Users(String username, int age, String password) {
        this.username = username;
        this.age = age;
        this.password = password;
    }
}

// ---------- JSON SERIALIZER ----------
class JsonSerializer {

    public static String toJson(Object obj) {

        StringBuilder json = new StringBuilder();
        json.append("{");

        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();

        boolean first = true;

        try {
            for (Field field : fields) {

                if (field.isAnnotationPresent(JsonField.class)) {

                    field.setAccessible(true);
                    JsonField annotation = field.getAnnotation(JsonField.class);

                    if (!first) {
                        json.append(", ");
                    }

                    json.append("\"")
                        .append(annotation.name())
                        .append("\": ");

                    Object value = field.get(obj);

                    if (value instanceof String) {
                        json.append("\"").append(value).append("\"");
                    } else {
                        json.append(value);
                    }

                    first = false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        json.append("}");
        return json.toString();
    }
}

// ---------- MAIN CLASS ----------
public class JsonFieldAnnotationDemo {

    public static void main(String[] args) {

        Users user = new Users("Ansh", 21, "secret123");

        String json = JsonSerializer.toJson(user);

        System.out.println(json);
    }
}
