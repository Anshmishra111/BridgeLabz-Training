package stream_api;

import java.util.*;
import java.util.stream.*;

// Claim class
class Claim {
    String type;
    double amount;

    Claim(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
}

// Main class
public class InsuranceClaimAnalysis {
    public static void main(String[] args) {

        List<Claim> claims = Arrays.asList(
            new Claim("Health", 5000),
            new Claim("Health", 8000),
            new Claim("Vehicle", 12000),
            new Claim("Vehicle", 10000),
            new Claim("Life", 20000)
        );

        Map<String, Double> avgClaimAmount =
            claims.stream()
                  .collect(Collectors.groupingBy(
                      c -> c.type,
                      Collectors.averagingDouble(c -> c.amount)
                  ));

        avgClaimAmount.forEach(
            (type, avg) ->
                System.out.println(type + " : " + avg)
        );
    }
}
