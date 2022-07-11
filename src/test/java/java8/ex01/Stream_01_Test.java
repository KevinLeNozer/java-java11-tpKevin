package java8.ex01;

import java8.data.Data;
import java8.data.domain.Order;
import java8.data.domain.Pizza;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 01 - Recherche
 */
public class Stream_01_Test {

	@Test
	public void test_stream_filter() throws Exception {
		List<Pizza> pizzas = new Data().getPizzas();

		// TODO récupérer la liste des pizzas dont le prix est >= 1300
		List<Pizza> listePizzas = pizzas.stream().filter(pizza -> pizza.getPrice() >= 1300).collect(Collectors.toList());
		List<Pizza> result = listePizzas;

		assertThat(result, hasSize(3));
		assertThat(result, everyItem(hasProperty("price", anyOf(equalTo(1300), greaterThan(1300)))));
	}

	@Test
	public void test_stream_filter_collect_counting() throws Exception {
		List<Pizza> pizzas = new Data().getPizzas();

		// TODO compter le nombre de pizzas dont le prix est >= 1300
		long nbrPizzaPrice = pizzas.stream().filter(pizza -> pizza.getPrice() >= 1300).map(p -> p.getPrice()).count();
		long result = nbrPizzaPrice;

		assertThat(result, is(3L));
	}

	@Test
	public void test_stream_anyMatch() throws Exception {
		List<Pizza> pizzas = new Data().getPizzas();

		// TODO valider si au moins une pizza à un prix >= 1300
		Boolean pizzaAnyMatch = pizzas.stream().anyMatch(pizza -> pizza.getPrice() >= 1300);
		Boolean result1 = pizzaAnyMatch;

		// TODO valider si au moins une pizza à un prix >= 2000
		Boolean pizzaAnyMatch2 = pizzas.stream().anyMatch(pizza -> pizza.getPrice() >= 2000);
		Boolean result2 = pizzaAnyMatch2;

		assertThat(result1, is(true));
		assertThat(result2, is(false));
	}

	@Test
	public void test_stream_allMatch() throws Exception {

		List<Pizza> pizzas = new Data().getPizzas();

		// TODO valider que toutes les pizzas ont un prix >= 1300
		Boolean pizzasAllMatch = pizzas.stream().allMatch(pizza -> pizza.getPrice() >= 1300);
		Boolean result1 = pizzasAllMatch;

		// TODO valider que toutes les pizzas ont un prix >= 900
		Boolean pizzasAllMatch2 = pizzas.stream().allMatch(pizza -> pizza.getPrice() >= 900);
		Boolean result2 = pizzasAllMatch2;

		assertThat(result1, is(false));
		assertThat(result2, is(true));
	}

	@Test
	public void test_stream_noneMatch() throws Exception {

		List<Pizza> pizzas = new Data().getPizzas();

		// TODO valider qu'aucune pizza n'a un prix >= 2000
		Boolean pizzasNoneMatch = pizzas.stream().noneMatch(pizza -> pizza.getPrice() >= 2000);
		Boolean result1 = pizzasNoneMatch;

		assertThat(result1, is(true));
	}

	@Test
	public void test_stream_findFirst() throws Exception {
		List<Order> orders = new Data().getOrders();

		// TODO récupérer une commande faite par un client dont le prénom est "Sophie"
		Optional<Order> ordersFindFirst = orders.stream().filter(order -> order.equals("Sophie")).findFirst();
		if (ordersFindFirst.isPresent()) {
			System.out.println(ordersFindFirst);
		}

		Optional<Order> result = ordersFindFirst;
		assertThat(result.isPresent(), is(false));
	}

	/**
	 * FACULTATIVE : PLUS DURE !!
	 */
	@Test
	public void test_stream_filter_adv() throws Exception {
		List<Order> orders = new Data().getOrders();
		// TODO récupérer la liste de toutes les commandes qui contiennent au moins une
		// pizza Pépéroni
		List<Order> ordersPizza = orders.stream().filter(order -> order.getPizzas().stream().anyMatch(pizza -> pizza.getName().equals("Pépéroni"))).collect(Collectors.toList());
		List<Order> result = ordersPizza;

		assertThat(result, hasSize(3));
	}
}
