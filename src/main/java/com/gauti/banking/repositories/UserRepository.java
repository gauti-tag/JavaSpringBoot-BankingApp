package com.gauti.banking.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gauti.banking.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // select * from user where firstname = 'ali'
    List<User> findByFirstname(String firstname);

    // select * from user where firstname like '%Ali%'
    List<User> findByFirstnameContaining(String firstname);

    
    // select * from user where firstname ilike 'ali'
    List<User> findByFirstnameContainingIgnoreCase(String firstname);

    // select * from user u inner join account a on u.id = a.id_user and a.iban = 'FBDR14789653'
    List<User> findAllByAccountIban(String iban);

    // select * from user where firstname = "%ali%" and email = "ali@gmail.com"
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    // utilisation du JPQL avec l'annotation @Query 
    @Query("from User where firstname = :fn")
    List<User> searchByFirstname(@Param("fn") String firstname);

    @Query("from User where firstname = '%:firstname%'")
    List<User> searchByFirstnameContaining(String firstname);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(String iban);
}
