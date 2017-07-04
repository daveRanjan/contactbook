package com.centralway.contactbook.service.impl;

import com.centralway.contactbook.model.Contact;
import com.centralway.contactbook.model.Entry;
import com.centralway.contactbook.model.User;
import com.centralway.contactbook.repository.ContactRepository;
import com.centralway.contactbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts(User user) {
        return contactRepository.findByUser(user);
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.delete(contactId);
    }

    @Override
    public Long addEntry(Long contactId, Entry entry) {
        Contact contact = contactRepository.findOne(contactId);
        contact.getPhones().add(entry.getPhone());
        return contactRepository.saveAndFlush(contact).getId();
    }

    @Override
    public Contact getContact(Long contactId) {
        return contactRepository.findOne(contactId);
    }
}
