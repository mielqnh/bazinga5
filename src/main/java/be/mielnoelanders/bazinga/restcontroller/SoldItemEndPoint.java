package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.SoldItem;
import be.mielnoelanders.bazinga.service.SoldItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customergames")
public class SoldItemEndPoint {

    // FIELDS
    private final SoldItemService service;

    // CONSTRUCTORS
    @Autowired
    public SoldItemEndPoint(SoldItemService service) {
        this.service = service;
    }

    //METHODS
    // --> init
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SoldItem> insertCustomerGames(@RequestBody SoldItem soldItem) {
        SoldItem test = service.addOne(soldItem);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<SoldItem>> getAll() {

        Iterable<SoldItem> customerGames = this.service.findAll();

        if (customerGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SoldItem> getById(@PathVariable Long id) {
        SoldItem result = this.service.findOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SoldItem> updateCustomerGames(@PathVariable Long id, @RequestBody SoldItem soldItem) {
        SoldItem probably = service.updateOneById(id, soldItem);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SoldItem> deleteById(@PathVariable Long id) {
        SoldItem result = service.deleteOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
