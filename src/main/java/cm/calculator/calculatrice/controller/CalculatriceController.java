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

        switch (request.getType()) {
            case "addition":
                result = request.getOperand1() + request.getOperand2();
                break;
            case "soustraction":
                result = request.getOperand1() - request.getOperand2();
                break;
            case "multiplication":
                result = request.getOperand1() * request.getOperand2();
                break;
            case "division":
                if (request.getOperand2() == 0) {
                    throw new IllegalArgumentException("Division par zéro non autorisée");
                }
                result = request.getOperand1() / request.getOperand2();
                break;
            case "sin":
                result = Math.sin(Math.toRadians(request.getOperand1()));
                break;
            case "cos":
                result = Math.cos(Math.toRadians(request.getOperand1()));
                break;
            case "tan":
                result = Math.tan(Math.toRadians(request.getOperand1()));
                break;
            case "exp":
                result = Math.exp(request.getOperand1());
                break;
            case "log":
                if (request.getOperand1() <= 0) {
                    throw new IllegalArgumentException("Le logarithme n'est défini que pour des nombres strictement positifs");
                }
                result = Math.log(request.getOperand1());
                break;
            default:
                throw new IllegalArgumentException("Type d'opération inconnu: " + request.getType());
        }

        return ResponseEntity.ok(result);
    }
}
