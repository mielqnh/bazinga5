package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.other.Customer;
import be.mielnoelanders.bazinga.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerEndPoint {

    // FIELDS
    private CustomerService service;

    // CONSTRUCTORS
    @Autowired
    public CustomerEndPoint(CustomerService service) {
        this.service = service;
    }

    // METHODS
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> addOne(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.addOne(customer), HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Customer>> findAll() {
        Iterable<Customer> customers = service.findAll();
        if (customers == null) {
            return new ResponseEntity<Iterable<Customer>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> findOneById(@PathVariable Long id) {
        Customer customerFound = service.findOneById(id);
        if (customerFound == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Customer>(customerFound, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateOneById(@PathVariable Long id, @RequestBody Customer customer) {
        Customer customerToUpdate = service.updateOneById(id, customer);
        if (customerToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerToUpdate, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteOneById(@PathVariable Long id) {
        Customer customerFound = service.findOneById(id);
        if (customerFound == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        } else {
            service.deleteOneById(id);
            return new ResponseEntity<Customer>(customerFound, HttpStatus.OK);
        }
    }
}
