package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.Publisher;
import be.mielnoelanders.bazinga.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publisher")
public class PublisherEndPoint {

    @Autowired
    private PublisherService publisherService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Publisher> addOne(@RequestBody Publisher publisher) {
        Publisher publisherAdd = publisherService.addOne(publisher);
        return new ResponseEntity<>(publisherAdd, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Publisher>> getAll() {
        Iterable<Publisher> publisherGetAll = this.publisherService.findAll();

        if (publisherGetAll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(publisherGetAll, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<Publisher> findByName(@PathVariable String name) {
        Publisher publisherFind = this.publisherService.findByName(name);

        if (publisherFind == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(publisherFind, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Publisher> updateOneById(@PathVariable Long id, @RequestBody Publisher publisher) {
        Publisher probably = publisherService.updateOneById(id, publisher);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    // Deze krijg ik niet aan het werk. Je geeft ook niet mee welke je wil veranderen.
/*    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Publisher> updatePublisherByName(@RequestBody Publisher publisher) {
        boolean publisherUpdate = publisherService.updatePublisherByName(publisher);

        if (publisherUpdate) {
            return new ResponseEntity<>(publisher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Publisher> deletePublisher(@PathVariable Long id) {
        boolean publisherDelete = publisherService.deleteOneById(id);

        if (publisherDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}