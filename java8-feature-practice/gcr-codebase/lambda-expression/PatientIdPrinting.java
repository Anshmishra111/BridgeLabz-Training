package lambda_expression;

import java.util.Arrays;
import java.util.List;

class Patient {
    int id;

    Patient(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

public class PatientIdPrinting {

    public static void main(String[] args) {

        List<Patient> patients = Arrays.asList(
            new Patient(101),
            new Patient(102),
            new Patient(103),
            new Patient(104)
        );

        // Print all patient IDs using method references
        patients.stream()
                .map(Patient::getId)      // method reference
                .forEach(System.out::println); // method reference
    }
}
