package br.comexport.avaliacao.controllers;

import br.comexport.avaliacao.entities.AnswerEntity;
import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.parameters.AnswerParameter;
import br.comexport.avaliacao.services.AnswerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/comexport/v1")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = AnswerEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })

    @GetMapping("/answers")
    public List<AnswerEntity> getAllAnswers() {

        return answerService.getAllAnswer();
    }

    @PostMapping(value = "/answer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postAnswer(@RequestBody AnswerParameter answerParameter)  {

        return answerService.applySave(answerParameter);
    }

    @GetMapping(value = "/answer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AnswerEntity getAnswer(@PathVariable(value = "id") Long id) {
        return answerService.getAnswer(id);
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable(value = "id") Long id) {
        return answerService.deleteAnswer(id);
    }

    @PutMapping("/answer/{id}")
    public ResponseEntity<Object> updateAnswer(@PathVariable(value = "id") Long id,
                                 @RequestBody AnswerParameter answerParameter) {

        return answerService.updateAnswer(id, answerParameter);
    }

}
