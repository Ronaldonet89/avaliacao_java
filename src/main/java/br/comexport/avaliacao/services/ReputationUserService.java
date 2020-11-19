package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.ReputationUserEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.ReputationUserParameter;
import br.comexport.avaliacao.repositories.ReputationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReputationUserService {

    @Autowired
    ReputationUserRepository reputationUserRepository;

    public ResponseEntity<Object> applySave(ReputationUserParameter reputationUserParameter)  {

        ReputationUserEntity reputationUserEntity = new ReputationUserEntity();
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
                .orElseThrow(() -> new ResourceNotFoundException("ReputationUser", "id_user", id));

        reputationUserRepository.delete(reputationUser);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateReputationUser(Long id, ReputationUserParameter reputationUserParameter){
        ReputationUserEntity reputationUser = reputationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReputationUser", "id_user", id));
        ReputationUserEntity reputationUserEntity = new ReputationUserEntity();
        reputationUserEntity.setScore(reputationUserParameter.getScore());

        reputationUserRepository.save(reputationUserEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reputationUserEntity);
    }

}
