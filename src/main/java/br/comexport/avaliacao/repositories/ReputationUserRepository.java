package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.ReputationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReputationUserRepository extends JpaRepository<ReputationUserEntity, Long> {

}
