package be.mielnoelanders.bazinga.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliergames")
public class SupplierGamesEndPoint {

//    @Autowired
//    private SupplierGamesService service;
//
//    @RequestMapping(value = "/getall", method = RequestMethod.GET)
//    public ResponseEntity<Iterable<Game>> getAll() {
//
//        Iterable<Game> games = this.service.getAll();
//
//        if (games == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(games, HttpStatus.OK);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Game> getById(@PathVariable Long id) {
//        Game result = this.service.getOne(id);
//
//        if (result == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Game> deleteById(@PathVariable Long id) {
//        Game result = service.deleteById(id);
//
//        if (result == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(result , HttpStatus.OK);
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Game> insertGame(@RequestBody Game game) {
//        Game test = service.insertGame(game);
//        return new ResponseEntity<>(test, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
//        Game probably = service.changeGame(id, game);
//        if (probably == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(probably, HttpStatus.OK);
//        }
//    }
//
//    @RequestMapping(value = "/findtitle/{title}", method = RequestMethod.GET)
//    public ResponseEntity<Iterable<Game>> lookUpByTitle(@PathVariable String title){
//        Iterable<Game> game = service.findByTitle(title);
//        if(game == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else{
//            return new ResponseEntity<>(game ,HttpStatus.OK);
//        }
//    }
//
}
