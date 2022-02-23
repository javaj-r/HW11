package question3.repository;

import lombok.Getter;
import question3.model.Category;
import question3.model.Customer;
import question3.model.Order;
import question3.model.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Repository {

    private final List<Category> categories = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

    public Repository save(Category category) {
        this.categories.add(category);
        return this;
    }

    public Repository save(Customer customer) {
        this.customers.add(customer);
        return this;
    }

    public Repository save(Order order) {
        this.orders.add(order);
        return this;
    }

    public Repository save(Product product) {
        this.products.add(product);
        return this;
    }
}
