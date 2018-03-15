package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Customer;

public interface CustomerService {

    // --> create (addOne)
    Customer addOne(Customer customer);


    // --> read (findAll & findOneById)
    Iterable<Customer> findAll();

    Customer findOneById(Long id);

    // --> update (updateOneById)
    Customer updateOneById(Long id, Customer customer);

    // --> delete (deleteOneById)
    void deleteOneById(Long id);

}
// --> others (Bla)