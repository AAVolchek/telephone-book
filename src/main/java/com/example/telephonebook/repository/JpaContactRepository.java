package com.example.telephonebook.repository;

import com.example.telephonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface JpaContactRepository extends JpaRepository<Contact, Long> {

    @Modifying
    @Transactional
    @Query("delete from Contact c where c.id=:id And c.user.id=:userId")
    void delete(@Param("id") Long id, @Param("userId") Long userId);

    @Query("select c from Contact c where c.user.id=:userId")
    List<Contact> getAll(@Param("userId") Long userId);

    @Query("select c from Contact c where c.id=:id And c.user.id=:userId")
    Contact getWithUser(@Param("id") Long id, @Param("userId") Long userId);

    @Query("select case when count(c) > 0" +
            "then true else false end " +
            "from Contact c " +
            "where c.id =:id And c.user.id=:userId")
    Boolean selectExistsUser(@Param("id") Long id, @Param("userId") Long userId);

}
