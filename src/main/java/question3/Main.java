package question3;

import lombok.NonNull;
import question3.model.Category;
import question3.model.Order;
import question3.model.Product;
import question3.repository.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    static Repository repository = new Repository();

    public static void main(String[] args) {
        new Bootstrap(repository);

        filterProductsByCategory(repository.getProducts(), repository.getCategories().get(1));

        orderedProductsInDateRange(repository.getOrders(), Date.valueOf("2021-01-02"), Date.valueOf("2021-01-04"));

        cheapestProductByCategory(repository.getProducts(), repository.getCategories().get(2));

        ordersAVGTotalPriceInDate(repository.getOrders(), Date.valueOf("2021-01-01"));
    }

    private static void filterProductsByCategory(@NonNull List<Product> products, @NonNull Category category) {
        System.out.println("\n1) Filter products by category " + category.getName() + " :\n");
        products.stream()
                .filter(product -> category.equals(product.getCategory()))
                .forEach(System.out::println);
    }

    private static void orderedProductsInDateRange(@NonNull List<Order> orders, @NonNull Date from, @NonNull Date to) {
        System.out.println("\n2) Products of orders from " + from + " to " + to + " :\n");

        orders.stream()
                .filter(order -> from.equals(order.getDate()) || from.before(order.getDate()))
                .filter(order -> to.equals(order.getDate()) || to.after(order.getDate()))
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);
    }

    private static void cheapestProductByCategory(@NonNull List<Product> products, @NonNull Category category) {
        System.out.println("\n3) The cheapest product with category " + category.getName() + " :\n");
        products.stream()
                .filter(product -> category.equals(product.getCategory()))
                .min(Comparator.comparingLong(Product::getPrice))
                .ifPresent(System.out::println);
    }

    private static void ordersAVGTotalPriceInDate(@NonNull List<Order> orders, @NonNull Date date) {
        System.out.println("\n4) Orders average of total price in date " + date + " :\n");

        System.out.print("Count : ");
        System.out.println(orders.stream()
                .filter(order -> date.equals(order.getDate()))
                .map(Order::getProducts)
                .mapToLong(Collection::size)
                .sum());

        System.out.print("Sum : ");
        System.out.println(orders.stream()
                .filter(order -> date.equals(order.getDate()))
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .mapToLong(Product::getPrice)
                .sum());

        System.out.print("Average : ");
        orders.stream()
                .filter(order -> date.equals(order.getDate()))
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .map(Product::getPrice)
                .mapToDouble(Long::doubleValue)
                .average()
                .ifPresent(System.out::println);
    }
}
