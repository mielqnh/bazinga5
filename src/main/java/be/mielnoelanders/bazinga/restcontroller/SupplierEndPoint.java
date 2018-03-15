package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.Supplier;
import be.mielnoelanders.bazinga.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
public class SupplierEndPoint {

    private SupplierService supplierService;

    @Autowired
    public SupplierEndPoint(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Supplier> addOne(@RequestBody Supplier supplier) {
        return new ResponseEntity<Supplier>(supplierService.addOne(supplier), HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Supplier>> findAll() {
        Iterable<Supplier> suppliers = supplierService.findAll();
        if (suppliers == null) {
            return new ResponseEntity<Iterable<Supplier>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Supplier> findByOneById(@PathVariable Long id) {
        Supplier supplierFound = supplierService.findOneById(id);
        if (supplierFound == null) {
            return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Supplier>(supplierFound, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Supplier> updateOneById(@PathVariable Long id, @RequestBody Supplier supplier) {
        Supplier supplierToUpdate = supplierService.updateOneById(id, supplier);
        if (supplierToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(supplierToUpdate, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Supplier> deleteOneById(@PathVariable Long id) {
        Supplier supplierFound = supplierService.findOneById(id);
        if (supplierFound == null) {
            return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
        } else {
            supplierService.deleteOneById(id);
            return new ResponseEntity<Supplier>(supplierFound, HttpStatus.OK);
        }
    }


}
