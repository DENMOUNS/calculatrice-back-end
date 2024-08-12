package cm.calculator.calculatrice.entity;

import java.util.List;

public record CalculatriceRequest(String type, List<Double> operands) {
}