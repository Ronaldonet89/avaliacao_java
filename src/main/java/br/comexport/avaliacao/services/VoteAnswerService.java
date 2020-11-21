package br.comexport.avaliacao.services;

import br.comexport.avaliacao.entities.VoteAnswerEntity;
import br.comexport.avaliacao.exception.ResourceNotFoundException;
import br.comexport.avaliacao.parameters.VoteAnswerParameter;
import br.comexport.avaliacao.repositories.VoteAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteAnswerService {

    @Autowired
    VoteAnswerRepository voteAnswerRepository;

    public ResponseEntity<Object> applySave(VoteAnswerParameter voteAnswerParameter)  {

        VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
        voteAnswerEntity.setScore(voteAnswerParameter.getScore());
        //voteAnswerEntity.setId_question(voteAnswerParameter.getId_question());
        //voteAnswerEntity.setId_user(voteAnswerParameter.getId_user());
        //voteAnswerEntity.setId_answer(voteAnswerParameter.getId_answer());
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
        VoteAnswerEntity voteAnswer = voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VoteAnswer", "id", id));

        VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
        voteAnswerEntity.setScore(voteAnswerParameter.getScore());

        voteAnswerRepository.save(voteAnswerEntity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(voteAnswerEntity);
    }

}
