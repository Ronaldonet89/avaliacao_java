package br.comexport.avaliacao.controllers;

import br.comexport.avaliacao.entities.FlagsEntity;
import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.parameters.FlagsParameter;
import br.comexport.avaliacao.services.FlagsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/comexport/v1")
public class FlagsController {

    @Autowired
    FlagsService flagsService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = FlagsEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })

    @GetMapping("/flags")
    public List<FlagsEntity> getAllFlags() {

        return flagsService.getAllFlags();
    }

    @PostMapping(value = "/flags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postFlags(@RequestBody FlagsParameter flagsParameter)  {

        return flagsService.applySave(flagsParameter);
    }

    @GetMapping(value = "/flags/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FlagsEntity getFlags(@PathVariable(value = "id") Long id) {
        return flagsService.getFlags(id);
    }

    @DeleteMapping("/flags/{id}")
    public ResponseEntity<?> deleteFlags(@PathVariable(value = "id") Long id) {
        return flagsService.deleteFlags(id);
    }

    @PutMapping("/flags/{id}")
    public ResponseEntity<Object> updateFlags(@PathVariable(value = "id") Long id,
                                 @RequestBody FlagsParameter flagsParameter) {

        return flagsService.updateFlags(id, flagsParameter);
    }

}
