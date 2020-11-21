package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.QuestionEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.QuestionParameter;
import br.comexport.avaliacao.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<Object> applySave(QuestionParameter questionParameter)  {

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setComment(questionParameter.getComment());
        //questionEntity.setId_flag(questionParameter.getId_flag());
        //questionEntity.setId_user(questionParameter.getId_user());
        questionEntity.setResolved(true);
        questionRepository.save(questionEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionEntity);
    }

    public List<QuestionEntity> getAllQuestion(){
        return questionRepository.findAll();
    }

    public QuestionEntity getQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
    }

    public ResponseEntity<?> deleteQuestion(Long id){
        QuestionEntity question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));

        questionRepository.delete(question);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateQuestion(Long id, QuestionParameter questionParameter){
        QuestionEntity question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setComment(questionParameter.getComment());
        questionEntity.setResolved(questionParameter.isResolved());

        questionRepository.save(questionEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionEntity);
    }

}
