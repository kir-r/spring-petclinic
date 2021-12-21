package org.springframework.samples.petclinic.coverage;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coverage")
class BasicConstructionsController {

    @ApiOperation(value = "Testing if-else statement",
        notes = "Entered value should be true or false. " +
            "Method returns its text value. " +
            "Coverage: 66.7%. Total Instructions count: 6.")
    @GetMapping("/branches/{value}")
    public String ifElseBranches(@PathVariable("value") boolean isTrue) {
        if (isTrue) {
            return "Yeah, it's true!";
        } else {
            return "False, no way!";
        }
    }

    @ApiOperation(value = "Testing Switch-case statement",
        notes = "Entered value should be int from 1 to 5. " +
            "Method returns its text value. " +
            "Coverage with values 1-5: 57.7%, " +
            "coverage with value >5: 46.2%. Total Instructions count: 26.")
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

    @ApiOperation(value = "Testing For loop",
        notes = "Method adds entered value to StringBuilder " +
            "random number of times from 2 to 10. " +
            "Total Instructions count: 26. " +
            "Coverage: 100%.")
    @GetMapping("/for/{value}")
    public String forLoop(@PathVariable("value") String value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < (2 + Math.random() * 10); i++) {
            stringBuilder.append(value).append(" ");
        }
        return stringBuilder.toString();
    }

    @ApiOperation(value = "Testing Lambda method",
        notes = "Method concatenates entered value using lambda method. " +
            "Total Instructions count: 7. " +
            "Coverage: 100%.")
    @GetMapping("/lambda/{value}")
    public String lambda(@PathVariable("value") String value) {
        Function<String, String> func = str -> str + " from lambda method";
        return func.apply(value);
    }

    @ApiOperation(value = "Testing Lambda reference method",
        notes = "Method returns entered value in a lower case. " +
            "Total Instructions count: 7. " +
            "Coverage: 100%.")
    @GetMapping("/lambdaRef/{value}")
    public String lambdaReference(@PathVariable("value") String value) {
        Function<String, String> func = String::toLowerCase;
        return func.apply(value);
    }

    @ApiOperation(value = "Testing While loop",
        notes = "Do-while loop adds entered value in StringBuilder while " +
            "random number from 2 to 10 is more than 5, but at least does one iteration. " +
            "Total Instructions count: 21. " +
            "Coverage: 100%.")
    @GetMapping("/while/{value}")
    public String whileLoop(@PathVariable("value") String value) {
        StringBuilder stringBuilder = new StringBuilder();
        do {
            stringBuilder.append(value).append(" ");
        } while ((2 + Math.random() * 10) > 5);
        return stringBuilder.toString();
    }

    @ApiOperation(value = "Testing labels",
        notes = "Loop \"for\" increases entered value random number of times, " +
            "from 2 to 10. If random number is 3 - loop label is \"continue\", " +
            "if random number is 9 - loop label is \"break\". " +
            "StringBuilder shows number of iterations and entered value. " +
            "Total Instructions count: 38. " +
            "Coverage: 94.7%, if loop doesn't reach continue, " +
            "Coverage: 97.4%, if break. " +
            "Coverage: 100%, if value >= 10 symbols.")
    @GetMapping("/labels/{value}")
    public String labels(@PathVariable("value") String value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
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

    @ApiOperation(value = "Testing Stream API",
        notes = "Initial collection is a list of Solar system planets, " +
            "Earth and Saturn present in duplicate with different register. " +
            "Stream does filter, does uppercase and creates a collection " +
            "from entered value. " +
            "Total Instructions count: 56. " +
            "Coverage: 100%.")
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

    @ApiOperation(value = "Testing Try-catch statement",
        notes = "Trying to parse entered value to int, then " +
            "catching NumberFormatException in case the value is not a number, " +
            "finally adds value in a string with exception message. " +
            "Total Instructions count: 24. " +
            "Coverage: 83.3% with entered number. " +
            "Coverage: 87.5% with entered text and exception.")
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

    @ApiOperation(value = "Testing complex if-else statement",
        notes = "If value has more than 10 letters it provides \"To many letters\", " +
            "if value has less than 2 letters it provides \"To few letters\", " +
            "if value is \"123\" it provides \"Try harder!\", " +
            "if value is \"0\" it provides \"Null in request\". " +
            "Total Instructions count: 26. " +
            "Coverage: 69.2% with entered value 123. " +
            "Coverage: 38.5% if too many letters. " +
            "Coverage: 53.8% if too few letters. " +
            "Coverage: 23.1% if 0 in request. " +
            "Coverage: 69.2% if it returns value.")
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
