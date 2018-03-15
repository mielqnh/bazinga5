package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Customer;
import be.mielnoelanders.bazinga.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // --> init
    @PostConstruct
    private void init() {
        Customer customer1 = new Customer();
        customer1.setName("cust1_name");
        customer1.setFirstName("cust1_firstname");
        customer1.setPhoneNumber("cust1_phonenumber");
        customer1.setGoodCustomer(true);
        Customer customer2 = new Customer();
        customer2.setName("cust2_name");
        customer2.setFirstName("cust2_firstname");
        customer2.setPhoneNumber("cust2_phonenumber");
        customer2.setGoodCustomer(false);
        Customer customer3 = new Customer();
        customer3.setName("cust3_name");
        customer3.setFirstName("cust3_firstname");
        customer3.setPhoneNumber("cust3_phonenumber");
        customer3.setGoodCustomer(true);
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
    }

    // --> create
    @Override
    public Customer addOne(Customer customer) {
        return customerRepository.save(customer);
    }


    // --> read
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findOneById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

// --> update

    @Override
    public Customer updateOneById(Long id, Customer customer) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            Customer customerToUpdate = foundCustomer.get();
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setFirstName(customer.getFirstName());
            customerToUpdate.setTotalSpent(customer.getTotalSpent());
            customerToUpdate.setGoodCustomer(customer.isGoodCustomer());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
            return customerRepository.save(customerToUpdate);
        } else {
            return null;
        }
    }

    // --> delete (deleteOneById)
    @Override
    public void deleteOneById(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
    }

// --> others

}
