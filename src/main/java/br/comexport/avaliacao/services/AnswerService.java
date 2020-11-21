package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.AnswerEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.AnswerParameter;
import br.comexport.avaliacao.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public ResponseEntity<Object> applySave(AnswerParameter answerParameter)  {

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setComment(answerParameter.getComment());
        //answerEntity.setId_question(answerParameter.getId_question());
        //answerEntity.setId_user(answerParameter.getId_user());

        answerRepository.save(answerEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(answerEntity);
    }

    public List<AnswerEntity> getAllAnswer(){
        return answerRepository.findAll();
    }

    public AnswerEntity getAnswer(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", id));
    }

    public ResponseEntity<?> deleteAnswer(Long id){
        AnswerEntity answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", id));

        answerRepository.delete(answer);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateAnswer(Long id, AnswerParameter answerParameter){
        AnswerEntity answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", id));

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setComment(answerParameter.getComment());

        answerRepository.save(answerEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(answerEntity);
    }

}
