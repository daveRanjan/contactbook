package com.centralway.contactbook.controller;

import com.centralway.contactbook.model.Contact;
import com.centralway.contactbook.model.Entry;
import com.centralway.contactbook.model.User;
import com.centralway.contactbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts(new User());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Contact createContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

    @RequestMapping(value = "/{contactId}",method = RequestMethod.POST)
    public Contact updateContact(@RequestBody Contact contact, @PathVariable Long contactId){
        contact.setId(contactId);
        return contactService.saveContact(contact);
    }

    @RequestMapping(value = "/{contactId}",method = RequestMethod.POST)
    public Contact getContact( @PathVariable Long contactId){
        return contactService.getContact(contactId);
    }

    @RequestMapping(value = "/{contactId}",method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable Long contactId){
        contactService.deleteContact(contactId);
    }

    @RequestMapping(value = "/{contactId}/entries", method = RequestMethod.POST)
    public void addEntry(@PathVariable Long contactId, @RequestBody Entry entry){
        contactService.addEntry(contactId,entry);
    }
}