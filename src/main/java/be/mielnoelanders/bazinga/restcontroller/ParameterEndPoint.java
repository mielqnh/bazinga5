package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.other.Parameter;
import be.mielnoelanders.bazinga.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parameter")
public class ParameterEndPoint {

    @Autowired
    private ParameterService service;

// --> create
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Parameter> addOne(@RequestBody Parameter parameter) {
        Parameter parmAdd = service.addOne(parameter);
        return new ResponseEntity<>(parmAdd, HttpStatus.CREATED);
    }

// --> read
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Parameter>> findAll() {
        Iterable<Parameter> parmGetAll = this.service.findAll();

        if (parmGetAll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(parmGetAll, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Parameter> findById(@PathVariable Long id) {
        Parameter parmFind = this.service.findOneById(id);

        if (parmFind == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(parmFind, HttpStatus.OK);
        }
    }

// --> update
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Parameter> updateOneById(@PathVariable Long id, @RequestBody Parameter parameter) {
        Parameter parmUpdate = service.updateOneById(id, parameter);

        if (parmUpdate != null) {
            return new ResponseEntity<>(parameter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

// --> delete
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Parameter> deleteOneById(@PathVariable Long id) {
        Parameter result = service.deleteOneById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
    }
}