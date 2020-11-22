package br.comexport.avaliacao.repositories;

import br.comexport.avaliacao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select u.* from user u \n" +
            "where Upper(u.Name) LIKE Upper(:name) \n" +
            "and u.email LIKE :email \n" +
            "and u.Birthdate = :birthdate",
            nativeQuery = true)
    List<UserEntity> selectUser(@Param("name") String name, @Param("email") String email, @Param("birthdate") Date birthdate);

    @Query(value = "select u.* from user u \n" +
            "where Upper(u.Name) LIKE Upper(:name) ", nativeQuery = true)
    List<UserEntity> selectUserName(@Param("name") String name);

    @Query(value = "select u.* from user u \n" +
            "where u.email LIKE :email ", nativeQuery = true)
    List<UserEntity> selectUserEmail(@Param("email") String email);

    @Query(value = "select u.* from user u \n" +
            "where u.Birthdate = :birthdate", nativeQuery = true)
    List<UserEntity> selectUserBirthdate(@Param("birthdate") Date birthdate);

    @Query(value = "select u.* from user u \n" +
            "where Upper(u.Name) LIKE Upper(:name) \n" +
            "and u.email LIKE :email \n",
            nativeQuery = true)
    List<UserEntity> selectUserNameEmail(@Param("name") String name, @Param("email") String email);

    @Query(value = "select u.* from user u \n" +
            "where u.email LIKE :email \n" +
            "and u.Birthdate = :birthdate",
            nativeQuery = true)
    List<UserEntity> selectUserEmailBirthdate(@Param("email") String email, @Param("birthdate") Date birthdate);

    @Query(value = "select u.* from user u \n" +
            "where Upper(u.Name) LIKE Upper(:name) \n" +
            "and u.Birthdate = :birthdate",
            nativeQuery = true)
    List<UserEntity> selectUserNameBirthdate(@Param("name") String name, @Param("birthdate") Date birthdate);


    @Query(value = "select u.* from user u \n" +
            "where u.id_role = :id_role ", nativeQuery = true)
    List<UserEntity> selectUserRole(@Param("id_role") Long id_role);

}
