package question3.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
public class Customer {

    private String name;
    private List<Order> orders = new ArrayList<>();

    public Customer saveOrder(Order order) {
        order.setCustomer(this);
        orders.add(order);
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
