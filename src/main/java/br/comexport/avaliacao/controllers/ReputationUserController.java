package br.comexport.avaliacao.controllers;

import br.comexport.avaliacao.entities.ReputationUserEntity;
import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.parameters.ReputationUserParameter;
import br.comexport.avaliacao.services.ReputationUserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/comexport/v1")
public class ReputationUserController {

    @Autowired
    ReputationUserService reputationUserService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ReputationUserEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })

    @GetMapping("/reputations")
    public List<ReputationUserEntity> getAllReputations() {

        return reputationUserService.getAllReputation();
    }

    @PostMapping(value = "/reputation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postReputation(@RequestBody ReputationUserParameter reputationUserParameter)  {

        return reputationUserService.applySave(reputationUserParameter);
    }

    @GetMapping(value = "/reputation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReputationUserEntity getReputation(@PathVariable(value = "id") Long id) {
        return reputationUserService.getReputationUser(id);
    }

    @DeleteMapping("/reputation/{id}")
    public ResponseEntity<?> deleteReputation(@PathVariable(value = "id") Long id) {
        return reputationUserService.deleteReputationUser(id);
    }

    @PutMapping("/reputation/{id}")
    public ResponseEntity<Object> updateReputation(@PathVariable(value = "id") Long id,
                                 @RequestBody ReputationUserParameter reputationUserParameter) {

        return reputationUserService.updateReputationUser(id, reputationUserParameter);
    }

}
