package cm.calculator.calculatrice;

import cm.calculator.calculatrice.entity.CalculatriceRequest;
import cm.calculator.calculatrice.entity.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatriceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testAddition() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.ADDITION, List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(6), response.getBody());
    }

    @Test
    void testSubtraction() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.SOUSTRACTION, List.of(BigDecimal.valueOf(10), BigDecimal.valueOf(4), BigDecimal.valueOf(3)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(3), response.getBody());
    }

    @Test
    void testMultiplication() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.MULTIPLICATION, List.of(BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(4)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(24), response.getBody());
    }

    @Test
    void testDivision() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.DIVISION, List.of(BigDecimal.valueOf(20), BigDecimal.valueOf(5)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(4), response.getBody());
    }

    @Test
    void testSin() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.SIN, List.of(BigDecimal.valueOf(90)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_HALF_UP), response.getBody().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    void testCos() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.COS, List.of(BigDecimal.valueOf(0)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_HALF_UP), response.getBody().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    void testTan() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.TAN, List.of(BigDecimal.valueOf(45)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_HALF_UP), response.getBody().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    void testLog() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.LOG, List.of(BigDecimal.valueOf(Math.E)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_HALF_UP), response.getBody().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    void testExp() {
        CalculatriceRequest request = new CalculatriceRequest(OperationType.EXP, List.of(BigDecimal.valueOf(1)));
        ResponseEntity<BigDecimal> response = restTemplate.postForEntity(createURL("/api/calculate"), request, BigDecimal.class);
        assertEquals(BigDecimal.valueOf(Math.E).setScale(2, BigDecimal.ROUND_HALF_UP), response.getBody().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
