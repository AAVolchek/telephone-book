package com.example.telephonebook.repository;

import com.example.telephonebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("" +
            " select case when count (u) > 0 then " +
            "true else false end " +
            "from User u " +
            "Where u.email =:email"
    )
    Boolean selectExistsEmail(@Param("email") String email);

    @Query
    Optional<User> findByEmail(@Param("email") String email);
}
