package es.upm.tfmaida.servlet;

import es.upm.tfmaida.Parser;
import es.upm.tfmaida.TxtWriter;
import io.swagger.annotations.ApiOperation;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vroddon
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ParserController {
    
    @ApiOperation(value = "Test of the functionality", notes = "You should carefully document your method here", response = String.class, tags = "aida")
    @PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/license2rdf")
    public String create(@RequestBody String texto) {
        
        String res="";
        try {
            res = TxtWriter.Text2RDF(texto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    @RequestMapping("/about")
    public String about(){
        return "Aida";
    }
    
    
}
