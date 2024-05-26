package com.example.kms.repository;

import com.example.kms.entity.Token;
import com.example.kms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    /*@Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)*/
    List<Token> findAllValidTokenByUser(User user);
    Optional<Token> findByToken(String token);
}
