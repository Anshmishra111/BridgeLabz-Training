package reflection;

import java.lang.annotation.*;

//---------- CUSTOM ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Author {
 String name();
}

//---------- CLASS USING ANNOTATION ----------
@Author(name = "Author Name")
class Book {
 // class content
}

//---------- MAIN CLASS ----------
public class AnnotationRuntimeDemo {

 public static void main(String[] args) {

     // Get Class object
     Class<Book> cls = Book.class;

     // Check if annotation is present
     if (cls.isAnnotationPresent(Author.class)) {

         // Retrieve annotation
         Author author = cls.getAnnotation(Author.class);

         System.out.println("Author Name: " + author.name());
     }
 }
}
