package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.InStoreItem;
import be.mielnoelanders.bazinga.service.InStoreItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliergames")
public class InStoreItemEndPoint {

    // FIELDS
    private final InStoreItemService service;

    // CONSTRUCTORS
    @Autowired
    public InStoreItemEndPoint(InStoreItemService service) {
        this.service = service;
    }

    // METHODS
    // --> init
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<InStoreItem> addOne(@RequestBody InStoreItem inStoreItem) {
        InStoreItem test = service.addOne(inStoreItem);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<InStoreItem>> findAll() {

        Iterable<InStoreItem> supplierGames = this.service.findAll();

        if (supplierGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(supplierGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<InStoreItem> findOneById(@PathVariable Long id) {
        InStoreItem result = this.service.findOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<InStoreItem> updateOneById(@PathVariable Long id, @RequestBody InStoreItem inStoreItem) {
        InStoreItem probably = service.updateOneById(id, inStoreItem);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<InStoreItem> deleteOneById(@PathVariable Long id) {
        InStoreItem result = service.deleteOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}