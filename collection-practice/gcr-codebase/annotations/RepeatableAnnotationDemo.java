package annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;

// ---------- CONTAINER ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

// ---------- REPEATABLE ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

// ---------- CLASS USING REPEATABLE ANNOTATION ----------
class SoftwareModule {

    @BugReport(description = "NullPointerException occurs on startup")
    @BugReport(description = "UI freezes when clicking submit")
    public void runModule() {
        System.out.println("Module running");
    }
}

// ---------- MAIN CLASS ----------
public class RepeatableAnnotationDemo {

    public static void main(String[] args) throws Exception {

        Method method = SoftwareModule.class.getMethod("runModule");

        BugReport[] reports = method.getAnnotationsByType(BugReport.class);

        for (BugReport report : reports) {
            System.out.println("Bug: " + report.description());
        }
    }
}
