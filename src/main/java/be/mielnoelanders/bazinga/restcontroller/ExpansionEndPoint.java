package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.basicitems.Expansion;
import be.mielnoelanders.bazinga.service.ExpansionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expansion")
public class ExpansionEndPoint {

    // FIELDS
    private final ExpansionService service;

    // CONSTRUCTORS
    @Autowired
    public ExpansionEndPoint(ExpansionService service) {
        this.service = service;
    }

    // METHODS
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expansion> addOne(@RequestBody Expansion game) {
        Expansion test = service.addOne(game);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Expansion> getOneById(@PathVariable Long id) {
        Expansion result = this.service.findOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Expansion>> getAll() {

        Iterable<Expansion> games = this.service.findAll();

        if (games == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Expansion> updateOneById(@PathVariable Long id, @RequestBody Expansion game) {
        Expansion probably = service.updateOneById(id, game);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Expansion> deleteOneById(@PathVariable Long id) {
        Expansion result = service.deleteOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }

    // --> others
    @RequestMapping(value = "/findonebytitle/{title}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Expansion>> findOneByTitle(@PathVariable String name){
        Iterable<Expansion> game = service.findOneByName(name);
        if(game == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(game ,HttpStatus.OK);
        }
    }
}
