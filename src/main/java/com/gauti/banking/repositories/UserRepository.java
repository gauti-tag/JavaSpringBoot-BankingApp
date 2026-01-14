package com.gauti.banking.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

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
}
