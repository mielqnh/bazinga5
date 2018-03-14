package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.SupplierGames;
import be.mielnoelanders.bazinga.service.SupplierGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliergames")
public class SupplierGamesEndPoint {

    @Autowired
    private SupplierGamesService service;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<SupplierGames>> getAll() {

        Iterable<SupplierGames> supplierGames = this.service.getAll();

        if (supplierGames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(supplierGames, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SupplierGames> getById(@PathVariable Long id) {
        SupplierGames result = this.service.getOne(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SupplierGames> deleteById(@PathVariable Long id) {
        SupplierGames result = service.deleteById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SupplierGames> insertSupplierGames(@RequestBody SupplierGames supplierGames) {
        SupplierGames test = service.insertSupplierGames(supplierGames);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SupplierGames> updateSupplierGames(@PathVariable Long id, @RequestBody SupplierGames supplierGames) {
        SupplierGames probably = service.changeSupplierGames(id, supplierGames);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }
}