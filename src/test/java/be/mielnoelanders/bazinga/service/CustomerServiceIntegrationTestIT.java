package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Customer;
import be.mielnoelanders.bazinga.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTestIT {// de naam van een integratietest klasse het best laten eindigen op 'IT' zodat Maven deze test
                                              // pas als laatste uitvoert waardoor de maven-build sneller verloopt !!

    @Autowired
    private CustomerService customerService;

    @Test
    public void createReadUpdateDeleteCustomerTest() {
        Customer newCustomer = new Customer();
        newCustomer.setName("New Customer to insert");

        //test create customer
        Customer insertedCustomer = customerService.addOne(newCustomer);
        long newId = insertedCustomer.getId();
        Assert.assertFalse(newId == 0);

        //test read customer
        Customer readCustomer = customerService.findOne(newId);
        Assert.assertEquals(newCustomer.getName(), readCustomer.getName());

        //test update customer
        readCustomer.setName("Supplier updated");
        Customer updatedCustomer = customerService.updateOne(newId, readCustomer);
        Assert.assertEquals("Supplier updated", updatedCustomer.getName());

        //test delete customer
        customerService.deleteById(newId);
        Assert.assertNull(customerService.findOne(newId));
    }
}
