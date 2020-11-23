package br.comexport.avaliacao.controllers;

import br.comexport.avaliacao.entities.AnswerEntity;
import br.comexport.avaliacao.entities.VoteAnswerEntity;
import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.parameters.VoteAnswerParameter;
import br.comexport.avaliacao.services.VoteAnswerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/comexport/v1")
public class VoteAnswerController {

    @Autowired
    VoteAnswerService voteAnswerService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = AnswerEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })

    @GetMapping("/vote-answers")
    public List<VoteAnswerEntity> getAllVoteAnswers() {

        return voteAnswerService.getAllVoteAnswer();
    }

    @PostMapping(value = "/vote-answer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postVoteAnswer(@RequestBody VoteAnswerParameter voteAnswerParameter)  {

        return voteAnswerService.applySave(voteAnswerParameter);
    }

    @GetMapping(value = "/vote-answer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteAnswerEntity getVoteAnswer(@PathVariable(value = "id") Long id) {
        return voteAnswerService.getVoteAnswer(id);
    }

    @DeleteMapping("/vote-answer/{id}")
    public ResponseEntity<?> deleteVoteAnswer(@PathVariable(value = "id") Long id) {
        return voteAnswerService.deleteVoteAnswer(id);
    }

    @PutMapping("/vote-answer/{id}")
    public ResponseEntity<Object> updateVoteAnswer(@PathVariable(value = "id") Long id,
                                 @RequestBody VoteAnswerParameter voteAnswerParameter) {

        return voteAnswerService.updateVoteAnswer(id, voteAnswerParameter);
    }

    @GetMapping(value = "/vote-answer-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getVoteAnswerAll(@RequestParam (value = "name", defaultValue = "", required=false) String name,
                                                   @RequestParam (value = "email", defaultValue = "", required=false) String email,
                                                   @RequestParam (value = "question",defaultValue = "", required=false) String question,
                                                   @RequestParam (value = "answer",defaultValue = "", required=false) String answer) {
        return voteAnswerService.consultAll(name, email, question, answer);
    }

}
