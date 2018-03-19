package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.transferitems.PurchaseReceipt;
import be.mielnoelanders.bazinga.service.InStoreItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instoreitem")
public class InStoreItemEndPoint {

    // FIELDS
    private final InStoreItemService service;

    // CONSTRUCTORS
    @Autowired
    public InStoreItemEndPoint(InStoreItemService service) {
        this.service = service;
    }

    // METHODS
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PurchaseReceipt> addOne(@RequestBody PurchaseReceipt purchaseReceipt) {
        PurchaseReceipt test = service.addOne(purchaseReceipt);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<PurchaseReceipt>> findAll() {

        Iterable<PurchaseReceipt> supplierGames = this.service.findAll();

        if (supplierGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(supplierGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PurchaseReceipt> findOneById(@PathVariable Long id) {
        PurchaseReceipt result = this.service.findOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PurchaseReceipt> updateOneById(@PathVariable Long id, @RequestBody PurchaseReceipt purchaseReceipt) {
        PurchaseReceipt probably = service.updateOneById(id, purchaseReceipt);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PurchaseReceipt> deleteOneById(@PathVariable Long id) {
        PurchaseReceipt result = service.deleteOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}