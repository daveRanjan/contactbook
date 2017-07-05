package com.centralway.contactbook.repository;

import com.centralway.contactbook.model.Contact;
import com.centralway.contactbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByUser(User user);

    Contact findByIdAndUserId(Long id, Long userId);
}
