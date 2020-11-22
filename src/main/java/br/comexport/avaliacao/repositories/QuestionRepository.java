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
            "where id_flag = :id_flag ", nativeQuery = true)
    List<QuestionEntity> selectQuetionFlads(@Param("id_flag") Long id_flag);

}
