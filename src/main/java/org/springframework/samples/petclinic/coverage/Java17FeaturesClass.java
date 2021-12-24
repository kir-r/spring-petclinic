package org.springframework.samples.petclinic.coverage;

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

    @GetMapping("/concatenation/{value}")
    public String concatenationOfConstants(@PathVariable("value") String value) {
        return "String concatenation " + value;
    }

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
