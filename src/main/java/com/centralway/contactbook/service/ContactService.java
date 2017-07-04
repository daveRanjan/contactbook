package com.centralway.contactbook.service;

import com.centralway.contactbook.model.Contact;
import com.centralway.contactbook.model.Entry;
import com.centralway.contactbook.model.User;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts(User user);

    Contact saveContact(Contact contact);

    void deleteContact(Long contactId);

    Long addEntry(Long contactId, Entry entry);

    Contact getContact(Long contactId);
}
