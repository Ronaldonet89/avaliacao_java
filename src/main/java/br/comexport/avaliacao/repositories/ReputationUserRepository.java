package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.ReputationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReputationUserRepository extends JpaRepository<ReputationUserEntity, Long> {

    @Query(value = "select r.* from reputation_user r \n" +
            "where r.id_user = :id_user ", nativeQuery = true)
    List<ReputationUserEntity> selectUser(@Param("id_user") Long id_user);

}
