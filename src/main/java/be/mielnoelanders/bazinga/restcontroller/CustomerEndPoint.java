package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.Customer;
import be.mielnoelanders.bazinga.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
