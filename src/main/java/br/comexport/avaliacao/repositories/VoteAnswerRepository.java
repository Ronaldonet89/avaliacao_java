package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.VoteAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteAnswerRepository extends JpaRepository<VoteAnswerEntity, Long> {

}
