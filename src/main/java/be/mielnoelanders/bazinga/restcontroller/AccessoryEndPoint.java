package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.basicitems.Accessory;
import be.mielnoelanders.bazinga.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accessory")
public class AccessoryEndPoint {

    // FIELDS
    private final AccessoryService service;

    // CONSTRUCTORS
    @Autowired
    public AccessoryEndPoint(AccessoryService service) {
        this.service = service;
    }

    // METHODS
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Accessory> addOne(@RequestBody Accessory game) {
        Accessory test = service.addOne(game);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Accessory> getOneById(@PathVariable Long id) {
        Accessory result = this.service.findOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Accessory>> getAll() {

        Iterable<Accessory> games = this.service.findAll();

        if (games == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Accessory> updateOneById(@PathVariable Long id, @RequestBody Accessory game) {
        Accessory probably = service.updateOneById(id, game);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Accessory> deleteOneById(@PathVariable Long id) {
        Accessory result = service.deleteOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }

    // --> others
    @RequestMapping(value = "/findonebytitle/{title}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Accessory>> findOneByTitle(@PathVariable String name){
        Iterable<Accessory> game = service.findOneByName(name);
        if(game == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(game ,HttpStatus.OK);
        }
    }
}
