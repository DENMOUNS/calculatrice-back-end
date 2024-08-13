package cm.calculator.calculatrice.controller;

import cm.calculator.calculatrice.entity.CalculatriceRequest;
import cm.calculator.calculatrice.entity.OperationType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.BinaryOperator;

@RestController
@RequestMapping("/api")
public class CalculatriceController {

    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);

    @PostMapping("/calculate")
    public ResponseEntity<BigDecimal> calculate(@RequestBody CalculatriceRequest request) {
        List<BigDecimal> operands = request.operands();
        OperationType operationType = request.type();
        BigDecimal result = performOperation(operationType, operands);

        return ResponseEntity.ok(result);
    }

    private BigDecimal performOperation(OperationType type, List<BigDecimal> operands) {
        return switch (type) {
            case ADDITION -> applyBinaryOperation(operands, BigDecimal::add);
            case SOUSTRACTION -> applyBinaryOperation(operands, BigDecimal::subtract);
            case MULTIPLICATION -> applyBinaryOperation(operands, BigDecimal::multiply);
            case DIVISION -> applyDivision(operands);
            case SIN, COS, TAN -> calculateTrigonometricFunction(operands.get(0), type);
            case EXP -> BigDecimal.valueOf(Math.exp(operands.get(0).doubleValue())).round(MATH_CONTEXT);
            case LOG -> calculateLog(operands.get(0));
            default -> throw new IllegalArgumentException("Type d'opération inconnu: " + type);
        };
    }

    private BigDecimal applyBinaryOperation(List<BigDecimal> operands, BinaryOperator<BigDecimal> operator) {
        return operands.stream().reduce(operator).orElse(BigDecimal.ZERO);
    }

    private BigDecimal applyDivision(List<BigDecimal> operands) {
        if (operands.contains(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Division par zéro non autorisée");
        }
        return operands.stream().reduce(BigDecimal::divide).orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateTrigonometricFunction(BigDecimal operand, OperationType type) {
        double value = operand.doubleValue();
        double result = switch (type) {
            case SIN -> Math.sin(Math.toRadians(value));
            case COS -> Math.cos(Math.toRadians(value));
            case TAN -> Math.tan(Math.toRadians(value));
            default -> throw new IllegalArgumentException("Fonction trigonométrique inconnue: " + type);
        };
        return BigDecimal.valueOf(result).round(MATH_CONTEXT);
    }

    private BigDecimal calculateLog(BigDecimal operand) {
        if (operand.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le logarithme n'est défini que pour des nombres strictement positifs");
        }
        return BigDecimal.valueOf(Math.log(operand.doubleValue())).round(MATH_CONTEXT);
    }
}
