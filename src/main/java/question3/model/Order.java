package question3.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
public class Order {

    private Date date;
    private Customer customer;
    private List<Product> products = new ArrayList<>();

    public long getTotalPrice() {
        return products.stream()
                .mapToLong(Product::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", totalPrice=" + getTotalPrice() +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(date, order.date) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, customer, products);
    }
}
