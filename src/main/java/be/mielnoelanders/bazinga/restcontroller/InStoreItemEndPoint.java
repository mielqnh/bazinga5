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

    @Autowired
    private InStoreItemService service;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<InStoreItem>> getAll() {

        Iterable<InStoreItem> supplierGames = this.service.getAll();

        if (supplierGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(supplierGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<InStoreItem> getById(@PathVariable Long id) {
        InStoreItem result = this.service.getOne(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<InStoreItem> deleteById(@PathVariable Long id) {
        InStoreItem result = service.deleteById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<InStoreItem> insertSupplierGames(@RequestBody InStoreItem inStoreItem) {
        InStoreItem test = service.insertSupplierGames(inStoreItem);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<InStoreItem> updateSupplierGames(@PathVariable Long id, @RequestBody InStoreItem inStoreItem) {
        InStoreItem probably = service.changeSupplierGames(id, inStoreItem);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }
}