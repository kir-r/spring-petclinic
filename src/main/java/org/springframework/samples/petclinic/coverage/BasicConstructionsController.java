package org.springframework.samples.petclinic.coverage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/coverage")
class BasicConstructionsController {

    @GetMapping("/branches/{value}")
    public String ifElseBranches(@PathVariable("value") boolean isTrue) {
        if (isTrue) {
            return "Yeah, it's true!";
        } else {
            return "False, no way!";
        }
    }

    @GetMapping("/switch/{value}")
    public String doSwitch(@PathVariable("value") String value) {
        String returnedText = "not init";
        switch (value) {
            case ("1"):
                returnedText = "Uno";
                break;
            case ("2"):
                returnedText = "Dos";
                break;
            case ("3"):
                returnedText = "Tres";
                break;
            case ("4"):
                returnedText = "Cuatro";
                break;
            case ("5"):
                returnedText = "Cinco";
                break;
        }
        return returnedText;
    }

    @GetMapping("/for/{value}")
    public String forLoop(@PathVariable("value") String value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < (2 + Math.random() * 10); i++) {
            stringBuilder.append(value).append(" ");
        }
        return stringBuilder.toString();
    }

    @GetMapping("/lambda/{value}")
    public String lambda(@PathVariable("value") String value) {
        TestInterface testInterface;
        testInterface = (str -> str + " from lambda method");
        return testInterface.inputString(value);
    }

    @GetMapping("/lambdaRef/{value}")
    public String lambdaReference(@PathVariable("value") String value) {
        TestInterface testInterface = TestClassForReference::inputString;
        return testInterface.inputString(value);
    }

    @GetMapping("/while/{value}")
    public String whileLoop(@PathVariable("value") String value) {
        StringBuilder stringBuilder = new StringBuilder();
        do {
            stringBuilder.append(value).append(" ");
        } while ((2 + Math.random() * 10) > 5);
        return stringBuilder.toString();
    }

    @GetMapping("/labels/{value}")
    public String labels(@PathVariable("value") String value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < (2 + Math.random() * 10); i++) {
            if (i == 3) {
                continue;
            }
            stringBuilder.append(i).append(" ").append(value).append("\n");
            if (i == 9) {
                break;
            }
        }
        return stringBuilder.toString();
    }

    @GetMapping("/stream/{value}")
    public String stream(@PathVariable("value") String value) {
        List<String> listOfPlanets = new ArrayList<>();
        listOfPlanets.add("Mercury");
        listOfPlanets.add("Venus");
        listOfPlanets.add("Earth");
        listOfPlanets.add("earth");
        listOfPlanets.add("Mars");
        listOfPlanets.add("Jupiter");
        listOfPlanets.add("Saturn");
        listOfPlanets.add("saTURN");
        listOfPlanets.add("Uranus");
        listOfPlanets.add("Neptune");

        return listOfPlanets.stream()
            .filter((x) -> x.equalsIgnoreCase(value))
            .map(String::toUpperCase)
            .collect(Collectors.joining(" "));
    }

    @GetMapping("/try-catch/{value}")
    public String tryCatch(@PathVariable("value") String value) {
        StringBuilder stringBuilder = new StringBuilder();
        String exceptionMessage = "";
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            exceptionMessage = e.getMessage();
        } finally {
            stringBuilder.append(value).append(" ").append(exceptionMessage);
        }
        return stringBuilder.toString();
    }

    @GetMapping("/branches/complex/{value}")
    public String complexCombination(@PathVariable("value") String value) {
        if (!value.equals("0")) {
            if (value.length() > 10) {
                return "To many letters";
            } else if (value.length() < 2) {
                return "To few letters";
            }
            if (value.equals("123")) {
                return "Try harder!";
            } else {
                return value;
            }
        } else {
            return "Null in request";
        }
    }

}

@FunctionalInterface
interface TestInterface {
    String inputString(String input);
}

class TestClassForReference {
    public static String inputString(String input) {
        return input + " from lambda reference method";
    }
}
