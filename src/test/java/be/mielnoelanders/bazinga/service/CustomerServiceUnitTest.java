package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.other.Customer;
import be.mielnoelanders.bazinga.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceUnitTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock //is injected into customerService
    private CustomerRepository customerRepository;

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private Optional<Customer> optionalCustomer;
    private List<Customer> customers;

    @Before
    public void init() {
        customer1 = new Customer();
        customer1.setName("testcustomer1");
        customer2 = new Customer();
        customer2.setName("testcustomer2");
        customer3 = new Customer();
        customer3.setName("testcustomer3");
        optionalCustomer = Optional.of(customer2);
        customers = new ArrayList<>();
        customers.addAll(Arrays.asList(customer1, customer2, customer3));
    }

    @Test
    public void testFindById() {
        when(this.customerRepository.findById(2L)).thenReturn(optionalCustomer);
        Customer customerFromService = customerService.findOneById(2L);
        assertThat(customerFromService.getName()).isEqualTo("testcustomer2");
        verify(customerRepository, times(1)).findById(2L);
    }

    @Test
    public void testFindAll() {
        when(this.customerRepository.findAll()).thenReturn(customers);
        Iterable<Customer> customerFromService = customerService.findAll();
        //test op name van eerste customer in de List customers
        assertThat(((List<Customer>) customerFromService).get(0).getName()).isEqualTo("testcustomer1");
        //test of aantal suppliers in de List customers 3 is
        assertThat((List<Customer>) customerFromService).size().isEqualTo(3);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testAddOne() {
        when(customerRepository.save(customer3)).thenReturn(customer3);
        Customer addedCustomer = customerService.addOne(customer3);
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getName()).isEqualTo("testcustomer3");
        verify(customerRepository, times(1)).save(customer3);
    }

    @Test
    public void testDeleteById() {
        when(customerRepository.existsById(3L)).thenReturn(true);
        customerService.deleteOneById(3L);
        verify(customerRepository, times(1)).existsById(3L);
        verify(customerRepository, times(1)).deleteById(3L);
    }

    @Test
    public void testUpdateOne() {
        when(customerRepository.findById(2L)).thenReturn(optionalCustomer);
        when(customerRepository.save(optionalCustomer.get())).thenReturn(customer3);
        Customer updatedCustomer = customerService.updateOneById(2L, optionalCustomer.get());
        System.out.println("Updated supplier = " + updatedCustomer);
        assertThat(updatedCustomer.getName()).isEqualTo("testcustomer3");
        verify(customerRepository, times(1)).findById(2L);
        verify(customerRepository, times(1)).save(optionalCustomer.get());

    }
}