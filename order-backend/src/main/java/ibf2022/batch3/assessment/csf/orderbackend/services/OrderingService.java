package ibf2022.batch3.assessment.csf.orderbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;

@Service
public class OrderingService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrdersRepo;

	// TODO: Task 5
	// WARNING: DO NOT CHANGE THE METHOD'S SIGNATURE
	public PizzaOrder placeOrder(PizzaOrder order) throws OrderException {
		System.out.println(">>> Inside the ordering service!");

		String pricingServiceUrl = "https://pizza-pricing-production.up.railway.app/order";

		MultiValueMap<String, String> pricingRequestBody = new LinkedMultiValueMap<>();
		pricingRequestBody.add("name", order.getName());
		pricingRequestBody.add("email", order.getEmail());
		pricingRequestBody.add("sauce", order.getSauce());
		pricingRequestBody.add("size", order.getSize().toString());
		pricingRequestBody.add("thickCrust", order.getThickCrust().toString());
		pricingRequestBody.add("toppings", order.getToppings().toString());
		pricingRequestBody.add("comments", order.getComments());

		System.out.println(pricingRequestBody);

		return null;
	}

	// For Task 6
	// WARNING: Do not change the method's signature or its implemenation
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		return ordersRepo.getPendingOrdersByEmail(email);
	}

	// For Task 7
	// WARNING: Do not change the method's signature or its implemenation
	public boolean markOrderDelivered(String orderId) {
		return ordersRepo.markOrderDelivered(orderId) && pendingOrdersRepo.delete(orderId);
	}

}
