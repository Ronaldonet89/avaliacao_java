package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.VoteAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoteAnswerRepository extends JpaRepository<VoteAnswerEntity, Long> {

    @Query(value = "select v.* from vote_answer v \n" +
            "where v.id_user = :id_user ", nativeQuery = true)
    List<VoteAnswerEntity> selectUser(@Param("id_user") Long id_user);

    @Query(value = "select v.* from vote_answer v \n" +
            "where v.id_question = :id_question ", nativeQuery = true)
    List<VoteAnswerEntity> selectQuestion(@Param("id_question") Long id_question);

    @Query(value = "select v.* from vote_answer v \n" +
            "where v.id_answer = :id_answer ", nativeQuery = true)
    List<VoteAnswerEntity> selectAnswer(@Param("id_answer") Long id_answer);

    @Query(value = "select v.*, max(v.score) vscore, max(r.score) score from user u \n" +
            "join question q on (u.id = q.Id_User) \n" +
            "join answer a on (u.id = a.Id_User) \n" +
            "join reputation_user r on (r.id_user) \n" +
            "join vote_answer v on ( v.id_question = q.id and v.id_answer = a.id and v.id_user = u.id ) \n"+
            "where Upper(u.name) LIKE Upper(:name) \n"+
            "and Upper(u.email) like Upper(:email) \n" +
            "and Upper(q.comment) like Upper(:question) \n" +
            "and Upper(a.comment) like Upper(:answer) ", nativeQuery = true)
    List<VoteAnswerEntity> consultAll(@Param("name") String name,
                                      @Param("email") String email,
                                      @Param("question") String question,
                                      @Param("answer") String answer);



}
