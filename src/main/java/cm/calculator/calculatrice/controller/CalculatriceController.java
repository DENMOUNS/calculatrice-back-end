package cm.calculator.calculatrice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cm.calculator.calculatrice.entity.CalculatriceRequest;

@RestController
@RequestMapping("/api")
public class CalculatriceController {

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody CalculatriceRequest request) {
        double result;

        // Utilisation du pattern matching pour le switch
        result = switch (request.getType()) {
            case "addition" -> request.getOperand1() + request.getOperand2();
            case "soustraction" -> request.getOperand1() - request.getOperand2();
            case "multiplication" -> request.getOperand1() * request.getOperand2();
            case "division" -> {
                if (request.getOperand2() == 0) {
                    throw new IllegalArgumentException("Division par zéro non autorisée");
                }
                yield request.getOperand1() / request.getOperand2();
            }
            case "sin" -> Math.sin(Math.toRadians(request.getOperand1()));
            case "cos" -> Math.cos(Math.toRadians(request.getOperand1()));
            case "tan" -> Math.tan(Math.toRadians(request.getOperand1()));
            case "exp" -> Math.exp(request.getOperand1());
            case "log" -> {
                if (request.getOperand1() <= 0) {
                    throw new IllegalArgumentException("Le logarithme n'est défini que pour des nombres strictement positifs");
                }
                yield Math.log(request.getOperand1());
            }
            default -> throw new IllegalArgumentException("Type d'opération inconnu: " + request.getType());
        };

        return ResponseEntity.ok(result);
    }
}
