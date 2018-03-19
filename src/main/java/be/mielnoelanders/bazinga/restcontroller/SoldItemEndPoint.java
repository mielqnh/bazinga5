package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.transferitems.SalesReceipt;
import be.mielnoelanders.bazinga.service.SoldItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solditem")
public class SoldItemEndPoint {

    // FIELDS
    private final SoldItemService service;

    // CONSTRUCTORS
    @Autowired
    public SoldItemEndPoint(SoldItemService service) {
        this.service = service;
    }

    //METHODS
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SalesReceipt> addOne(@RequestBody SalesReceipt salesReceipt) {
        SalesReceipt test = service.addOne(salesReceipt);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<SalesReceipt>> findAll() {

        Iterable<SalesReceipt> customerGames = this.service.findAll();

        if (customerGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SalesReceipt> findOneById(@PathVariable Long id) {
        SalesReceipt result = this.service.findOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SalesReceipt> updateOneById(@PathVariable Long id, @RequestBody SalesReceipt salesReceipt) {
        SalesReceipt probably = service.updateOneById(id, salesReceipt);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SalesReceipt> deleteOneById(@PathVariable Long id) {
        SalesReceipt result = service.deleteOneById(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
