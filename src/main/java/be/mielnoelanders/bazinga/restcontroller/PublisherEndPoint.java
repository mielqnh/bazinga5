package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.other.Publisher;
import be.mielnoelanders.bazinga.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publisher")
public class PublisherEndPoint {

    @Autowired
    private PublisherService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Publisher> addOne(@RequestBody Publisher publisher) {
        Publisher publisherAdd = service.addOne(publisher);
        return new ResponseEntity<>(publisherAdd, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Publisher>> findAll() {
        Iterable<Publisher> publisherGetAll = this.service.findAll();

        if (publisherGetAll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(publisherGetAll, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Publisher> findOneById(@PathVariable Long id) {
        Publisher publisherFind = this.service.findOneById(id);

        if (publisherFind == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(publisherFind, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Publisher> updateOneById(@PathVariable Long id, @RequestBody Publisher publisher) {
        Publisher probably = service.updateOneById(id, publisher);
        if (probably == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(probably, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Publisher> deleteOneById(@PathVariable Long id) {
        boolean publisherDelete = service.deleteOneById(id);

        if (publisherDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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