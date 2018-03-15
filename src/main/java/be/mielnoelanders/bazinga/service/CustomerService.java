package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Customer;

public interface CustomerService {

    // --> create
    Customer addOne(Customer customer);

    // --> read
    Iterable<Customer> findAll();

    Customer findOneById(Long id);

    // --> update
    Customer updateOneById(Long id, Customer customer);

    // --> delete
    void deleteOneById(Long id);
}