package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.*;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.VoteAnswerParameter;
import br.comexport.avaliacao.repositories.ReputationUserRepository;
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
    ReputationUserRepository reputationUserRepository;

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

        Long id_user = voteAnswerEntity.getUser().getId();
        List<ReputationUserEntity> reputationUserEntities = reputationUserRepository.selectUser(id_user);
        Double score = voteAnswerEntity.getScore();

        if(reputationUserEntities.size() > 0) {

            ReputationUserEntity reputationUserEntity = new ReputationUserEntity();
            reputationUserEntity.setUser(new UserEntity(id_user));
            reputationUserEntity.setScore(reputationUserEntities.get(0).getScore()+score);
            reputationUserEntity.setId(reputationUserEntities.get(0).getId());
            reputationUserRepository.save(reputationUserEntity);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(voteAnswerEntity);
    }

    public List<VoteAnswerEntity> getAllVoteAnswer(){
        return voteAnswerRepository.findAll();
    }

    public ResponseEntity<Object> consultAll(String name, String email, String question, String answer) {

        if (!name.isEmpty() && !email.isEmpty() && !question.isEmpty() && !answer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(voteAnswerRepository.consultAll("%"+name+"%", "%"+email+"%", "%"+question+"%", "%"+answer+"%"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Parametros invalidos!"));
        }
    }

    public VoteAnswerEntity getVoteAnswer(Long id) {
        return voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoteAnswer", "id", id));
    }

    public ResponseEntity<?> deleteVoteAnswer(Long id){

        VoteAnswerEntity voteAnswer = voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoteAnswer", "id", id));

        Long id_user = voteAnswer.getUser().getId();
        List<ReputationUserEntity> reputationUserEntities = reputationUserRepository.selectUser(id_user);
        Double score = voteAnswer.getScore();

        voteAnswerRepository.delete(voteAnswer);

        if(reputationUserEntities.size() > 0) {
            score = (score - reputationUserEntities.get(0).getScore());
            ReputationUserEntity reputationUserEntity = new ReputationUserEntity();
            reputationUserEntity.setUser(new UserEntity(id_user));
            reputationUserEntity.setScore(score);
            reputationUserEntity.setId(reputationUserEntities.get(0).getId());
            reputationUserRepository.save(reputationUserEntity);
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateVoteAnswer(Long id, VoteAnswerParameter voteAnswerParameter){

        VoteAnswerEntity voteAnswer = voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoteAnswer", "id", id));

        ResponseEntity<Object> isvalid = validVoteAnswer(voteAnswerParameter);
        if(isvalid != null)
            return isvalid;

        VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
        voteAnswerEntity.setScore(voteAnswerParameter.getScore());
        voteAnswerEntity.setQuestion(new QuestionEntity(voteAnswerParameter.getId_question()));
        voteAnswerEntity.setUser(new UserEntity(voteAnswerParameter.getId_user()));
        voteAnswerEntity.setAnswer(new AnswerEntity(voteAnswerParameter.getId_answer()));
        voteAnswerEntity.setId(id);
        voteAnswerRepository.save(voteAnswerEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(voteAnswerEntity);
    }

    public ResponseEntity<Object> validVoteAnswer(VoteAnswerParameter voteAnswerParameter){
        if ( voteAnswerParameter.getScore().longValue() <= 0 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","score não informado!"));
        }

        if ( voteAnswerParameter.getId_question().longValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Pergunta não informada!"));
        }

        if ( voteAnswerParameter.getId_user().longValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Usuário não informado!"));
        }
        if ( voteAnswerParameter.getId_answer().longValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Resposta não informada!"));
        }
        return null;
    }

}
