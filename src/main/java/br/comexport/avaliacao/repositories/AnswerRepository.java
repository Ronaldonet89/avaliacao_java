package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query(value = "select a.* from answer a \n" +
            "where a.id_user = :id_user ", nativeQuery = true)
    List<AnswerEntity> selectAnswerUser(@Param("id_user") Long id_user);

    @Query(value = "select a.* from answer a \n" +
            "where a.id_question = :id_question ", nativeQuery = true)
    List<AnswerEntity> selectAnswerQuestion(@Param("id_question") Long id_question);
}
