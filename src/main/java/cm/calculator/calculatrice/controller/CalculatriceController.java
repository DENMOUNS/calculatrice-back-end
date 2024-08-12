package cm.calculator.calculatrice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cm.calculator.calculatrice.entity.CalculatriceRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculatriceController {

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody CalculatriceRequest request) {
        List<Double> operands = request.operands();
        double result;

        result = switch (request.type()) {
            case "addition" -> operands.stream().mapToDouble(Double::doubleValue).sum();
            case "soustraction" -> operands.stream().reduce((a, b) -> a - b).orElse(0.0);
            case "multiplication" -> operands.stream().reduce(1.0, (a, b) -> a * b);
            case "division" -> {
                if (operands.contains(0.0)) {
                    throw new IllegalArgumentException("Division par zéro non autorisée");
                }
                yield operands.stream().reduce((a, b) -> a / b).orElse(0.0);
            }
            case "sin" -> Math.sin(Math.toRadians(operands.get(0)));
            case "cos" -> Math.cos(Math.toRadians(operands.get(0)));
            case "tan" -> Math.tan(Math.toRadians(operands.get(0)));
            case "exp" -> Math.exp(operands.get(0));
            case "log" -> {
                if (operands.get(0) <= 0) {
                    throw new IllegalArgumentException("Le logarithme n'est défini que pour des nombres strictement positifs");
                }
                yield Math.log(operands.get(0));
            }
            default -> throw new IllegalArgumentException("Type d'opération inconnu: " + request.type());
        };

        return ResponseEntity.ok(result);
    }
}
