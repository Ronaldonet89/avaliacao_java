package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select u.* from user u \n" +
            "where Upper(u.Name) like Upper(%:name%) \n" +
            "and u.email = :email \n" +
            "and date( u.Birthdate) = :birthdate")
    Page<UserEntity> selectUser(String name, String email, Date birthdate);

    @Query(value = "select u.* from user u \n" +
            "where Upper(u.Name) like Upper(%:name%) ")
    Page<UserEntity> selectUserName(String name);

    @Query(value = "select u.* from user u \n" +
            "where u.email = :email ")
    Page<UserEntity> selectUserEmail(String email);

    @Query(value = "select u.* from user u \n" +
            "where date( u.Birthdate) = :birthdate")
    Page<UserEntity> selectUserBirthdate(Date birthdate);

}
