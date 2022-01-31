package org.springframework.samples.petclinic.coverage;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/coverage-java17")
public class Java17FeaturesClass {

    @Operation(summary = "Testing Switch-case statement",
        description = "Entered value should be int from 1 to 5. " +
            "Method returns its text value. In case 5 it adds random int. " +
            "Coverage with with value 1: 42.3%, " +
            "with value 5: 65.4%, with value >5: 73.1%. " +
            "Total Instructions count: 26.")
    @GetMapping("/switch/{value}")
    public String doSwitch(@PathVariable("value") String value) {
        return switch (value) {
            case ("1") -> "Uno";
            case ("2") -> "Dos";
            case ("3") -> "Tres";
            case ("4") -> "Cuatro";
            case ("5") -> {
                int randomInt = new Random().nextInt();
                yield "Cinco with random int " + randomInt;
            }
            default -> "not init";
        };
    }

    @Operation(summary = "Testing string formatting",
        description = "Entered value concatenates to existing text. " +
            "Method returns string. " +
            "Coverage: 100%. " +
            "Total Instructions count: 3.")
    @GetMapping("/string/{value}")
    public String stringFormat(@PathVariable("value") String value) {
        return value + """

            This method designed
            with the aim of
            testing
            string features
            in 17 Java
            """;
    }

    @Operation(summary = "Testing teeing collector",
        description = "Teeing two collectors. Takes an entered value, sums it with number from 1 to 5 and " +
            "divides sum by amount of numbers (sum/count). " +
            "Entered value should be an int. " +
            "Coverage: 100%. " +
            "Total Instructions count: 42.")
    @GetMapping("/teeing/{value}")
    public String teeingCollector(@PathVariable("value") int value) {
        return Stream.of(1, 2, 3, 4, 5, value)
            .collect(Collectors.teeing(
                    Collectors.counting(),
                    Collectors.summingInt(n -> Integer.parseInt(n.toString())),
                    (count, sum) -> sum / count
                )
            ).toString();
    }

    @Operation(summary = "Testing concatenation of constants",
        description = "Entered value concatenates to existing text. " +
            "Method returns string. " +
            "Coverage: 100%. " +
            "Total Instructions count: 3.")
    @GetMapping("/concatenation/{value}")
    public String concatenationOfConstants(@PathVariable("value") String value) {
        return "String concatenation " + value;
    }

    @Operation(summary = "Testing Lambda method",
        description = "Method concatenates entered value using lambda method. " +
            "Total Instructions count: 7. " +
            "Coverage: 100%.")
    @GetMapping("/lambda/{value}")
    public String lambda(@PathVariable("value") String value) {
        Function<String, String> func = str -> str + " from lambda method";
        return func.apply(value);
    }
}

record TestRecordClass(int id, String description) implements TestSealedInterface {
}

sealed interface TestSealedInterface permits TestRecordClass {
}
