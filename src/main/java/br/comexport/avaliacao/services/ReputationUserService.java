package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.ReputationUserEntity;
import br.comexport.avaliacao.entities.UserEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.AnswerParameter;
import br.comexport.avaliacao.parameters.ReputationUserParameter;
import br.comexport.avaliacao.repositories.ReputationUserRepository;
import br.comexport.avaliacao.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReputationUserService {

    @Autowired
    ReputationUserRepository reputationUserRepository;

    @Autowired
    Util util;

    public ResponseEntity<Object> applySave(ReputationUserParameter reputationUserParameter)  {

        ResponseEntity<Object> isvalid = validReputationUser(reputationUserParameter);
        if(isvalid != null)
            return isvalid;

        ReputationUserEntity reputationUserEntity = new ReputationUserEntity();
        reputationUserEntity.setUser(new UserEntity(reputationUserParameter.getId_user()));
        reputationUserEntity.setScore(reputationUserParameter.getScore());
        reputationUserRepository.save(reputationUserEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reputationUserEntity);
    }

    public List<ReputationUserEntity> getAllReputation(){
        return reputationUserRepository.findAll();
    }

    public ReputationUserEntity getReputationUser(Long id) {
        return reputationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReputationUser", "id", id));
    }

    public ResponseEntity<?> deleteReputationUser(Long id){
        ReputationUserEntity reputationUser = reputationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReputationUser", "id", id));

        reputationUserRepository.delete(reputationUser);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateReputationUser(Long id, ReputationUserParameter reputationUserParameter){

        ResponseEntity<Object> isvalid = validReputationUser(reputationUserParameter);
        if(isvalid != null)
            return isvalid;

        ReputationUserEntity reputationUser = reputationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReputationUser", "id", id));
        ReputationUserEntity reputationUserEntity = new ReputationUserEntity();
        reputationUserEntity.setUser(new UserEntity(reputationUserParameter.getId_user()));
        reputationUserEntity.setScore(reputationUserParameter.getScore());

        reputationUserRepository.save(reputationUserEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reputationUserEntity);
    }

    public ResponseEntity<Object> validReputationUser(ReputationUserParameter reputationUserParameter){
        if ( reputationUserParameter.getId_user().intValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Usuário não informado!"));
        }

        if ( reputationUserParameter.getScore().intValue() < 1 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(util.exceptError("Dados inválidos","Score!"));
        }
        return null;
    }

}
