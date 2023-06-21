package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;

public class OrdersRepository {

	// create collection first when setting up mongo
	// db.createCollection("orders")
	// this "orders" collection is what we insert into
	@Autowired
	private MongoTemplate mongoTemplate;
	private static final String ORDER_COLLECTION = "orders";

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for add()

	// insert
	// db.orders.insertOne({
	//// _id: '1',
	//// date: ISODate("2023-06-01T17:00:00.000Z"),
	//// total: '50.00',
	//// name: 'John',
	//// email: 'john@doe.com',
	//// sauce: 'classic',
	//// size: 0,
	//// comments: 'no further comment',
	//// toppings: ['cheese', 'chicken', 'vegetables']
	// })

	public void add(PizzaOrder order) {
		// create a new bson document that will store the
		// new order to be inserted into the mongo database
		Document orderToInsert = new Document()
				.append("_id", order.getOrderId())
				.append("date", order.getDate())
				.append("total", order.getTotal())
				.append("name", order.getName())
				.append("email", order.getEmail())
				.append("sauce", order.getSauce())
				.append("size", order.getSize())
				.append("comments", order.getComments())
				.append("toppings", order.getToppings());

		mongoTemplate.insert(orderToInsert, ORDER_COLLECTION);
	}

	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for getPendingOrdersByEmail()
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {

		return null;
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for markOrderDelivered()
	public boolean markOrderDelivered(String orderId) {

		return false;
	}

}
