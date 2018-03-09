package be.mielnoelanders.bazinga.restcontroller;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;
import be.mielnoelanders.bazinga.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parm")
public class ParameterEndPoint {

    @Autowired
    private ParameterService parm;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Parameter> addParameter(@RequestBody Parameter parameter) {
        Parameter parmAdd = parm.addParameter(parameter);
        return new ResponseEntity<>(parmAdd, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Parameter>> getAll() {
        Iterable<Parameter> parmGetAll = this.parm.getAll();

        if (parmGetAll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(parmGetAll, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ResponseEntity<Parameter> findByType(@PathVariable ParameterEnum type) {
        Parameter parmFind = this.parm.findByType(type);

        if (parmFind == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(parmFind, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.PUT)
    public ResponseEntity<Parameter> updateParameterByType(@RequestBody Parameter parameter) {
        boolean parmUpdate = parm.updateParameterByType(parameter);

        if (parmUpdate) {
            return new ResponseEntity<>(parameter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Parameter> deleteParameter(@PathVariable Long id) {
        boolean parmDelete = parm.deleteParameter(id);

        if (parmDelete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
