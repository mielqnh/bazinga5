package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.CustomerGames;
import be.mielnoelanders.bazinga.service.CustomerGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customergames")
public class CustomerGamesEndPoint {

    @Autowired
    private CustomerGamesService service;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CustomerGames>> getAll() {

        Iterable<CustomerGames> customerGames = this.service.getAll();

        if (customerGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerGames> getById(@PathVariable Long id) {
        CustomerGames result = this.service.getOne(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CustomerGames> deleteById(@PathVariable Long id) {
        CustomerGames result = service.deleteById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CustomerGames> insertCustomerGames(@RequestBody CustomerGames customerGames) {
        CustomerGames test = service.insertCustomerGames(customerGames);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CustomerGames> updateCustomerGames(@PathVariable Long id, @RequestBody CustomerGames customerGames) {
        CustomerGames probably = service.changeCustomerGames(id, customerGames);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }
}
