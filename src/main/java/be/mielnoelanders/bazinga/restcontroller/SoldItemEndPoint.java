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

    @Autowired
    private SoldItemService service;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<SoldItem>> getAll() {

        Iterable<SoldItem> customerGames = this.service.getAll();

        if (customerGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customerGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SoldItem> getById(@PathVariable Long id) {
        SoldItem result = this.service.getOne(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SoldItem> deleteById(@PathVariable Long id) {
        SoldItem result = service.deleteById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SoldItem> insertCustomerGames(@RequestBody SoldItem soldItem) {
        SoldItem test = service.saveCustomerGames(soldItem);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SoldItem> updateCustomerGames(@PathVariable Long id, @RequestBody SoldItem soldItem) {
        SoldItem probably = service.changeCustomerGames(id, soldItem);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }
}
