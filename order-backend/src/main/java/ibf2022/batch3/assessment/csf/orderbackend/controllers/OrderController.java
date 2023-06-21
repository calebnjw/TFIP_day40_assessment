package ibf2022.batch3.assessment.csf.orderbackeand.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;

@RestController
@RequestMapping(path = "/api")
public class OrderController {

	// TODO: Task 3 - POST /api/order
	@PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> placeOrder(@RequestBody String payload) {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.contentType(MediaType.APPLICATION_JSON)
					.body(
							Json.createObjectBuilder()
									.add("orderId", "123")
									.build()
									.toString());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.contentType(MediaType.APPLICATION_JSON)
					.body(
							Json.createObjectBuilder()
									.add("error", e.getMessage())
									.build()
									.toString());
		}
	}

	// TODO: Task 6 - GET /api/orders/<email>

	// TODO: Task 7 - DELETE /api/order/<orderId>

}
