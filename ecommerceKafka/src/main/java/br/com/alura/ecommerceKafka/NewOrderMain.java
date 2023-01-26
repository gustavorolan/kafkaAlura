package br.com.alura.ecommerceKafka;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
	public static void main(String[] args)  {
		try (var dispatcher = new KafkaDispatcher<Order>()) {
			for (var i = 0; i < 10; i++) {
				var order = new Order(
						UUID.randomUUID().toString(),
						UUID.randomUUID().toString(),
						BigDecimal.valueOf(Math.random() * 5000));

				dispatcher.send("ECOMMERCE_NEW_ORDER", order.getUserId(), order);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
