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
        result = switch (request.type()) {
            case "addition" -> request.operand1() + request.operand2();
            case "soustraction" -> request.operand1() - request.operand2();
            case "multiplication" -> request.operand1() * request.operand2();
            case "division" -> {
                if (request.operand2() == 0) {
                    throw new IllegalArgumentException("Division par zéro non autorisée");
                }
                yield request.operand1() / request.operand2();
            }
            case "sin" -> Math.sin(Math.toRadians(request.operand1()));
            case "cos" -> Math.cos(Math.toRadians(request.operand1()));
            case "tan" -> Math.tan(Math.toRadians(request.operand1()));
            case "exp" -> Math.exp(request.operand1());
            case "log" -> {
                if (request.operand1() <= 0) {
                    throw new IllegalArgumentException("Le logarithme n'est défini que pour des nombres strictement positifs");
                }
                yield Math.log(request.operand1());
            }
            default -> throw new IllegalArgumentException("Type d'opération inconnu: " + request.type());
        };

        return ResponseEntity.ok(result);
    }
}
