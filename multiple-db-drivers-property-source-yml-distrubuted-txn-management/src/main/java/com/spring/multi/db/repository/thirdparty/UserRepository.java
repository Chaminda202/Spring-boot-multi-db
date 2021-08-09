package com.spring.multi.db.repository.thirdparty;


import com.spring.multi.db.model.thirdparty.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
