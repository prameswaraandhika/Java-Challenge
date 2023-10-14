import org.example.challenge3.model.Order;
import org.example.challenge3.model.OrderDetail;
import org.example.challenge3.model.Product;
import org.example.challenge3.service.ServiceOrder;
import org.example.challenge3.service.ServiceOrderImpl;
import org.example.challenge3.sql.InfoUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class ServiceOrderTest {


	ServiceOrder serviceOrder;
	@BeforeEach
	public void init(){
		serviceOrder = new ServiceOrderImpl();
	}

	@Test
	@DisplayName("Positive Test: untuk menambahkan test")
	public void testCreateOrder(){
		UUID orderId = UUID.randomUUID();
		Order order = Order.builder()
				.id(orderId)
				.userId(InfoUser.USER_ID)
				.orderTime(LocalDate.now())
				.destinationAdress(InfoUser.DESTINATION)
				.isComplete(true)
				.build();
		serviceOrder.createOrder(order);

		Order createdOrder = serviceOrder.getOrderByUserId(order.getUserId());
		Assertions.assertNotNull(createdOrder);
	}

	@Test
	@DisplayName("Positive Test: untuk menambahkan data order detail")
	public void testCreateOrderDetail(){
		int quantity = 3;
		Product product = Product.builder()
				.id(UUID.fromString("27cfb7a4-6e34-45da-8bf9-d6ec58ba4334"))
				.productName("Nasi Uduk + Ayam Bakar Madu")
				.price(15000.00)
				.merchantId(UUID.fromString("ec8940a4-eb82-4c4f-82a3-cad199147349"))
				.build();
		Double totalPrice = product.getPrice() * quantity;
		OrderDetail orderDetail = OrderDetail.builder()
				.orderId(UUID.fromString("723c0837-d7c0-464b-be0f-0054071aa466"))
				.id(UUID.randomUUID())
				.productId(product.getId())
				.quantity(quantity)
				.totalPrice(totalPrice)
				.build();
		serviceOrder.createOrderDetail(orderDetail);
		OrderDetail insertedOrderDetail = serviceOrder.getOrderDetailById(orderDetail.getId());
		Assertions.assertNotNull(insertedOrderDetail);
		Assertions.assertEquals(orderDetail, insertedOrderDetail, "Order detail should match the inserted order detail.");

	}
}
