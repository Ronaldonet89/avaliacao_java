package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.*;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.QuestionParameter;
import br.comexport.avaliacao.repositories.AnswerRepository;
import br.comexport.avaliacao.repositories.QuestionRepository;
import br.comexport.avaliacao.repositories.VoteAnswerRepository;
import br.comexport.avaliacao.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    VoteAnswerRepository voteAnswerRepository;

    @Autowired
    Util util;

    public ResponseEntity<Object> applySave(QuestionParameter questionParameter)  {

        ResponseEntity<Object> isvalid = validQuestion(questionParameter);
        if(isvalid != null)
            return isvalid;

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setComment(questionParameter.getComment());
        questionEntity.setFlag(new FlagsEntity(questionParameter.getId_flag()));
        questionEntity.setUser(new UserEntity(questionParameter.getId_user()));
        questionEntity.setResolved(questionParameter.isResolved());
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

        List<AnswerEntity> answerEntities = answerRepository.selectAnswerQuestion(id);

        if(answerEntities.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Não é possivel excluir pergunta sem antes excluir a resposta!"));
        }

        List<VoteAnswerEntity> voteAnswerEntities = voteAnswerRepository.selectQuestion(id);

        if( voteAnswerEntities.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Não é possivel excluir pergunta sem antes excluir o voto da resposta!"));
        }

        questionRepository.delete(question);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateQuestion(Long id, QuestionParameter questionParameter){
        ResponseEntity<Object> isvalid = validQuestion(questionParameter);
        if(isvalid != null)
            return isvalid;

        QuestionEntity question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setComment(questionParameter.getComment());
        questionEntity.setFlag(new FlagsEntity(questionParameter.getId_flag()));
        questionEntity.setUser(new UserEntity(questionParameter.getId_user()));
        questionEntity.setId(id);
        questionEntity.setResolved(questionParameter.isResolved());

        questionRepository.save(questionEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionEntity);
    }

    public ResponseEntity<Object> validQuestion(QuestionParameter questionParameter){
        if ( questionParameter.getComment().isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Comentário não informado!"));
        }

        if ( questionParameter.getId_flag().longValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Flag não informado!"));
        }

        if ( questionParameter.getId_user().longValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Usuário não informado!"));
        }
        return null;
    }

}
