package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Customer;

public interface CustomerService {


    // --> create (addOne)



// --> read (findAll & findOneById)



// --> update (updateOneById)



// --> delete (deleteOneById)



// --> others (Bla)


    // --> create
    Customer addCustomer(Customer customer);

    // --> read (findAllBla & findBlaById)
    Iterable<Customer> findAllCustomers();

    Customer findCustomerById(Long id);

    // --> update (updateBla)
    Customer updateCustomerById(Long id, Customer customer);

    // --> delete (deleteBlaById)
    void deleteCustomerById(Long id);
}
