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
    public Contact updateContact(Contact contact) {
        Contact existingContact = contactRepository.findByIdAndUserId(contact.getId(), contact
            .getUser()
            .getId());

        if (existingContact == null) {
            throw new IllegalArgumentException("Contact not found");
        }

        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        Contact existingContact = contactRepository.findByIdAndUserId(contact.getId(), contact
            .getUser()
            .getId());

        if (existingContact == null) {
            throw new IllegalArgumentException("Contact not found");
        }

        contactRepository.delete(contact);
    }

    @Override
    public Long addEntry(Entry entry, Contact contact) {
        Contact existingContact = contactRepository.findByIdAndUserId(contact.getId(), contact
            .getUser()
            .getId());

        if (existingContact == null) {
            throw new IllegalArgumentException("Contact not found");
        }

        contact.getPhones().add(entry.getPhone());
        return contactRepository.saveAndFlush(contact).getId();
    }

    @Override
    public Contact getContact(Contact contact) {
        return contactRepository.findByIdAndUserId(contact.getId(), contact
            .getUser()
            .getId());
    }
}
