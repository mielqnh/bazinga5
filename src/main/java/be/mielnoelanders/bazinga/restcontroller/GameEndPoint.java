package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.Game;
import be.mielnoelanders.bazinga.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameEndPoint {

    // FIELDS
    private final GameService service;

    // CONSTRUCTORS
    @Autowired
    public GameEndPoint(GameService service) {
        this.service = service;
    }

    // METHODS
    // --> init
    // --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Game> addOne(@RequestBody Game game) {
        Game test = service.addOne(game);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    // --> read
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Game> getOneById(@PathVariable Long id) {
        Game result = this.service.findOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Game>> getAll() {

        Iterable<Game> games = this.service.findAll();

        if (games == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
    }

    // --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Game> updateOneById(@PathVariable Long id, @RequestBody Game game) {
        Game probably = service.updateOneById(id, game);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Game> deleteOneById(@PathVariable Long id) {
        Game result = service.deleteOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }

    // --> others
    @RequestMapping(value = "/findonebytitle/{title}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Game>> findOneByTitle(@PathVariable String title){
        Iterable<Game> game = service.findOneByTitle(title);
        if(game == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(game ,HttpStatus.OK);
        }
    }
}
