package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.AnswerEntity;
import br.comexport.avaliacao.entities.QuestionEntity;
import br.comexport.avaliacao.entities.UserEntity;
import br.comexport.avaliacao.entities.VoteAnswerEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.VoteAnswerParameter;
import br.comexport.avaliacao.repositories.VoteAnswerRepository;
import br.comexport.avaliacao.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoteAnswerService {

    @Autowired
    VoteAnswerRepository voteAnswerRepository;

    @Autowired
    Util util;

    public ResponseEntity<Object> applySave(VoteAnswerParameter voteAnswerParameter)  {

        ResponseEntity<Object> isvalid = validVoteAnswer(voteAnswerParameter);
        if(isvalid != null)
            return isvalid;

        VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
        voteAnswerEntity.setScore(voteAnswerParameter.getScore());
        voteAnswerEntity.setQuestion(new QuestionEntity(voteAnswerParameter.getId_question()));
        voteAnswerEntity.setUser(new UserEntity(voteAnswerParameter.getId_user()));
        voteAnswerEntity.setAnswer(new AnswerEntity(voteAnswerParameter.getId_answer()));
        voteAnswerRepository.save(voteAnswerEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(voteAnswerEntity);
    }

    public List<VoteAnswerEntity> getAllVoteAnswer(){
        return voteAnswerRepository.findAll();
    }

    public VoteAnswerEntity getVoteAnswer(Long id) {
        return voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoteAnswer", "id", id));
    }

    public ResponseEntity<?> deleteVoteAnswer(Long id){
        VoteAnswerEntity voteAnswer = voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoteAnswer", "id", id));

        voteAnswerRepository.delete(voteAnswer);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateVoteAnswer(Long id, VoteAnswerParameter voteAnswerParameter){
        ResponseEntity<Object> isvalid = validVoteAnswer(voteAnswerParameter);
        if(isvalid != null)
            return isvalid;

        VoteAnswerEntity voteAnswer = voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoteAnswer", "id", id));

        VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
        voteAnswerEntity.setScore(voteAnswerParameter.getScore());

        voteAnswerRepository.save(voteAnswerEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(voteAnswerEntity);
    }

    public ResponseEntity<Object> validVoteAnswer(VoteAnswerParameter voteAnswerParameter){
        if ( voteAnswerParameter.getScore().intValue() <= 0 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","score não informado!"));
        }

        if ( voteAnswerParameter.getId_question().intValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Pergunta não informada!"));
        }

        if ( voteAnswerParameter.getId_user().intValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Usuário não informado!"));
        }
        if ( voteAnswerParameter.getId_answer().intValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Resposta não informada!"));
        }
        return null;
    }

}
