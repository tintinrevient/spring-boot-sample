package com.hncy.demo.repository;

import com.hncy.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u where u.fullname = ?1")
    User findByFullname(@Param("fullname") String fullname);

    List<User> findAll(Sort sort);

    Page<User> findAll(Pageable pageable);

}
