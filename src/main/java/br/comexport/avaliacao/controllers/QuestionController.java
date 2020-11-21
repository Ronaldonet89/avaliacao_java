package br.comexport.avaliacao.controllers;

import br.comexport.avaliacao.entities.QuestionEntity;
import br.comexport.avaliacao.errors.Error;
import br.comexport.avaliacao.parameters.QuestionParameter;
import br.comexport.avaliacao.services.QuestionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comexport/v1")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = QuestionEntity.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 422, message = "Unprocessable Entity", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })

    @GetMapping("/questions")
    public List<QuestionEntity> getAllQuestions() {

        return questionService.getAllQuestion();
    }

    @PostMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postQuestion(@RequestBody QuestionParameter questionParameter)  {

        return questionService.applySave(questionParameter);
    }

    @GetMapping(value = "/question/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionEntity getQuestion(@PathVariable(value = "id") Long id) {
        return questionService.getQuestion(id);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(value = "id") Long id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<Object> updateQuestion(@PathVariable(value = "id") Long id,
                                 @RequestBody QuestionParameter questionParameter) {

        return questionService.updateQuestion(id, questionParameter);
    }

}
