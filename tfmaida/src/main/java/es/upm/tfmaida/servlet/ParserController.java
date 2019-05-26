package es.upm.tfmaida.servlet;

import es.upm.tfmaida.Parser;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/parser/document")
public class ParserController {
    
    @ApiOperation(value = "Test of the functionality", notes = "You should carefully document your method here", response = String.class, tags = "aida")
    @PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public String create(@RequestBody String texto) {
        String res = "";
        String sentences[] = Parser.getSentences(texto, true);
        for (String sentence : sentences) {
            System.out.println(sentence);
            res+=sentence+" -----\n"; 
        }
        return res;
    }
}
