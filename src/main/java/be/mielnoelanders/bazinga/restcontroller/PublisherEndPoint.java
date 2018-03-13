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
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        Publisher publisherAdd = publisherService.addPublisher(publisher);
        return new ResponseEntity<>(publisherAdd, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Publisher>> getAll() {
        Iterable<Publisher> publisherGetAll = this.publisherService.getAll();

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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Publisher> updatePublisherByName(@RequestBody Publisher publisher) {
        boolean publisherUpdate = publisherService.updatePublisherByName(publisher);

        if (publisherUpdate) {
            return new ResponseEntity<>(publisher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Publisher> deleteParameter(@PathVariable Long id) {
        boolean publisherDelete = publisherService.deletePublisher(id);

        if (publisherDelete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}