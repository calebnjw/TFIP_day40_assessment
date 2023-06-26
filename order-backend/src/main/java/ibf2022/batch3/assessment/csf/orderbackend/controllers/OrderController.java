package ibf2022.batch3.assessment.csf.orderbackeand.controllers;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.services.OrderingService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

@RestController
@RequestMapping(path = "/api")
public class OrderController {

	@Autowired
	private OrderingService orderingService;

	// TODO: Task 3 - POST /api/order
	// taking the JSON data that is passed in from the post request (the
	// @RequestBody payload)
	// using a JSON object reader to create a JSON object (Json.createReader(new
	// StringReader(payload)).readObject())
	// then create a PizzaOrder object and place order, (take the stuff from the
	// json and put it into a java object)
	// then return PizzaResponse with JSON to front (pass the java object to the
	// frontend as a new json)

	// frontend
	// main.component: order data in json --> pizza.service: post request as
	// stringified json -->
	// backend
	// orderController: json string to java object --> orderingService: save to db

	// backend
	// orderingService: returns order object --> orderController: creates http
	// response with order details -->
	// frontend
	// pizza.service: sends upwards --> main.component: receives information,
	// directs user to next page

	@PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> placeOrder(@RequestBody String payload) {
		// create a JSON reader that takes in a string reader
		// then tell it to read the string object
		JsonObject pizzaJson = Json.createReader(new StringReader(payload)).readObject();

		PizzaOrder pizzaOrder = new PizzaOrder();

		pizzaOrder.setName(pizzaJson.getString("name"));
		pizzaOrder.setEmail(pizzaJson.getString("email"));
		pizzaOrder.setSize(pizzaJson.getInt("size"));
		pizzaOrder.setThickCrust(pizzaJson.getString("base").equalsIgnoreCase("thick"));
		pizzaOrder.setSauce(pizzaJson.getString("sauce"));

		JsonArray topppingsJsonArray = pizzaJson.getJsonArray("toppings");
		List<String> toppingsList = new LinkedList<>();
		for (JsonValue t : topppingsJsonArray) {
			toppingsList.add(t.toString());
		}

		pizzaOrder.setToppings(toppingsList);

		try {
			PizzaOrder orderResult = orderingService.placeOrder(pizzaOrder);

			JsonObject response = Json.createObjectBuilder()
					.add("orderId", orderResult.getOrderId())
					.add("date", orderResult.getDate().toString())
					.add("name", orderResult.getName())
					.add("email", orderResult.getEmail())
					.add("total", orderResult.getTotal())
					.build();

			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.contentType(MediaType.APPLICATION_JSON)
					.body(response.toString());

		} catch (Exception e) {
			e.printStackTrace();

			JsonObject response = Json.createObjectBuilder()
					.add("error", e.getMessage())
					.build();

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.contentType(MediaType.APPLICATION_JSON)
					.body(response.toString());
		}
	}

	// TODO: Task 6 - GET /api/orders/<email>

	// TODO: Task 7 - DELETE /api/order/<orderId>

}
