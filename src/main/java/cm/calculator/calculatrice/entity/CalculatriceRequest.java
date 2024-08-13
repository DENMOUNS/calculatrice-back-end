package cm.calculator.calculatrice.entity;

import java.math.BigDecimal;
import java.util.List;

public record CalculatriceRequest(OperationType type, List<BigDecimal> operands) {
}
