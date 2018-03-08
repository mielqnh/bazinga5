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


    @Autowired
    private GameService service;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Game>> getAll() {

        Iterable<Game> games = this.service.getAll();

        if (games == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Game> getById(@PathVariable Long id) {
        Game result = this.service.getOne(id);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Game> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Game> insertGame(@RequestBody Game game) {
        Game test = service.insertGame(game);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Game> updateCamera(@PathVariable Long id, @RequestBody Game game) {
        Game probably = service.changeGame(id, game);
        if (probably != null) {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/findtitle/{title}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Game>> lookUpByTitle(@PathVariable String title){
        Iterable<Game> kweetnie = service.findByTitle(title);
        if(kweetnie!= null){
            return new ResponseEntity<>(kweetnie,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
