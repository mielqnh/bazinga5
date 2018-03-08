package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Customer;

public interface CustomerService {

    //CRUD methods

    Customer addOne(Customer customer);

    Iterable<Customer> findAll();

    Customer findOne(Long id);

    Customer updateOne(Long id, Customer customer);

    void deleteById(Long id);
}
