package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.FlagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagsRepository extends JpaRepository<FlagsEntity, Long> {

}
