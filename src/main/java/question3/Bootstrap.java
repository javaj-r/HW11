package question3;

import question3.model.Category;
import question3.model.Customer;
import question3.model.Order;
import question3.model.Product;
import question3.repository.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bootstrap {

    private final Repository repository;

    public Bootstrap(Repository repository) {
        this.repository = repository;
        initSampleData();
    }

    private void initSampleData() {
        Category category1 = new Category().setName("Cat1");
        Category category2 = new Category().setName("Cat2");
        Category category3 = new Category().setName("Cat3");

        Customer customer1 = new Customer().setName("Customer1");
        Customer customer2 = new Customer().setName("Customer2");
        Customer customer3 = new Customer().setName("Customer3");

        repository.save(category1)
                .save(category2)
                .save(category3)
                .save(customer1)
                .save(customer2)
                .save(customer3);

        initProducts(1, category1);
        initProducts(11, category2);
        initProducts(21, category3);

        initOrders();
    }


    private void initProducts(int index, Category category) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = index; i < index + 10; i++) {
            repository.save(new Product()
                    .setName("Product" + i)
                    .setCategory(category)
                    .setPrice(random.nextLong(1000, 10001)));
        }
    }

    private void initOrders() {
        // customer1
        repository
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(0))
                        .setDate(getDate("2021-01-01"))
                        .setProducts(getSampleProducts(20, 2)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(0))
                        .setDate(getDate("2021-01-02"))
                        .setProducts(getSampleProducts(0, 4)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(0))
                        .setDate(getDate("2021-01-03"))
                        .setProducts(getSampleProducts(2, 3)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(0))
                        .setDate(getDate("2021-01-04"))
                        .setProducts(getSampleProducts(5, 6)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(0))
                        .setDate(getDate("2021-01-05"))
                        .setProducts(getSampleProducts(10, 4)));

        // customer2
        repository
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(1))
                        .setDate(getDate("2021-01-01"))
                        .setProducts(getSampleProducts(2, 5)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(1))
                        .setDate(getDate("2021-01-02"))
                        .setProducts(getSampleProducts(0, 3)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(1))
                        .setDate(getDate("2021-01-03"))
                        .setProducts(getSampleProducts(3, 4)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(1))
                        .setDate(getDate("2021-01-04"))
                        .setProducts(getSampleProducts(13, 3)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(1))
                        .setDate(getDate("2021-01-15"))
                        .setProducts(getSampleProducts(11, 4)));

        // customer3
        repository
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(2))
                        .setDate(getDate("2021-01-01"))
                        .setProducts(getSampleProducts(10, 4)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(2))
                        .setDate(getDate("2021-01-02"))
                        .setProducts(getSampleProducts(13, 3)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(2))
                        .setDate(getDate("2021-01-03"))
                        .setProducts(getSampleProducts(20, 3)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(2))
                        .setDate(getDate("2021-01-04"))
                        .setProducts(getSampleProducts(15, 3)))
                .save(new Order()
                        .setCustomer(repository.getCustomers().get(2))
                        .setDate(getDate("2021-01-08"))
                        .setProducts(getSampleProducts(18, 4)));
    }

    private List<Product> getSampleProducts(int index, int count) {
        List<Product> products = repository.getProducts();
        List<Product> result = new ArrayList<>();
        for (int i = index, j = 0; j < count; i += 3, j++) {
            if (i < products.size()) {
                result.add(products.get(i));
            }
        }

        return result;
    }

    private Date getDate(String stringDate) {
        try {
            return Date.valueOf(stringDate);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
