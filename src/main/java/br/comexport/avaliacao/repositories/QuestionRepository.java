package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query(value = "select q.* from question q \n" +
            "where q.id_flag = :id_flag ", nativeQuery = true)
    List<QuestionEntity> selectQuestionFlags(@Param("id_flag") Long id_flag);

    @Query(value = "select q.* from question q \n" +
            "where q.id_user = :id_user ", nativeQuery = true)
    List<QuestionEntity> selectQuestionUser(@Param("id_user") Long id_user);

}
