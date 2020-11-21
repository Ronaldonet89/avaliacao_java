package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.AnswerEntity;
import br.comexport.avaliacao.entities.QuestionEntity;
import br.comexport.avaliacao.entities.UserEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.AnswerParameter;
import br.comexport.avaliacao.parameters.QuestionParameter;
import br.comexport.avaliacao.repositories.AnswerRepository;
import br.comexport.avaliacao.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    Util util;

    public ResponseEntity<Object> applySave(AnswerParameter answerParameter)  {

        ResponseEntity<Object> isvalid = validAnswer(answerParameter);
        if(isvalid != null)
            return isvalid;

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setComment(answerParameter.getComment());
        answerEntity.setQuestion(new QuestionEntity(answerParameter.getId_question()));
        answerEntity.setUser(new UserEntity(answerParameter.getId_user()));

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

        ResponseEntity<Object> isvalid = validAnswer(answerParameter);
        if(isvalid != null)
            return isvalid;

        AnswerEntity answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", id));

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setComment(answerParameter.getComment());

        answerRepository.save(answerEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(answerEntity);
    }

    public ResponseEntity<Object> validAnswer(AnswerParameter answerParameter){
        if ( answerParameter.getComment().isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Comentário não informado!"));
        }

        if ( answerParameter.getId_question().intValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Pergunta não informada!"));
        }

        if ( answerParameter.getId_user().intValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Usuário não informado!"));
        }
        return null;
    }

}
