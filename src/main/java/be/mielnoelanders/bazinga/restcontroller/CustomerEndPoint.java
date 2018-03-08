package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.Customer;
import be.mielnoelanders.bazinga.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerEndPoint {

    private CustomerService customerService;

    @Autowired
    public CustomerEndPoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> addOne(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.addOne(customer), HttpStatus.CREATED);
    }

    @RequestMapping(value="getAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Customer>> getAll() {
        Iterable<Customer> customers = customerService.findAll();
        if (customers == null) {
            return new ResponseEntity<Iterable<Customer>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> findOne(@PathVariable Long id) {
        Customer customerFound = customerService.findOne(id);
        if (customerFound == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Customer>(customerFound, HttpStatus.OK);
        }
    }
}
