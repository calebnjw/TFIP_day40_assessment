package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import jakarta.json.Json;

@Repository
public class PendingOrdersRepository {

	// we use RedisTemplate instead of MongoTemplate for redis
	// makes sense?
	// RedisTemplate requires <key, value> datatypes to be defined
	// we use <String, String> because we save the json as string
	// and read from string when we retrieve it
	@Autowired
	@Qualifier("pending-orders")
	private RedisTemplate<String, String> redisTemplate;

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	public void add(PizzaOrder order) {
		String orderPending = Json.createObjectBuilder()
				.add("orderId", order.getOrderId())
				// date type not supported, need to stringify
				.add("date", order.getDate().toString())
				.add("total", order.getTotal())
				.add("name", order.getName())
				.add("email", order.getEmail())
				.build().toString();

		// here we save the order as key value
		// orderId will be the key that we look up order by,
		// with the orderPending json (converted to string) as value
		redisTemplate.opsForValue().append(order.getOrderId(), orderPending);
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	public boolean delete(String orderId) {
		return false;
	}

}
