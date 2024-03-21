package com.PFE.RH.Controllers;

import com.PFE.RH.DTO.ContactDTO;
import com.PFE.RH.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDTO> addContact(@RequestBody ContactDTO contactDTO) {
        ContactDTO createdContact = contactService.saveContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }
    //@PostMapping("/{contactId}/impots/{impotId}")
    /*public ResponseEntity<ContactDTO> addImpotToContact(@PathVariable Long contactId,
                                                        @PathVariable Long impotId) {
        ContactDTO updatedContact = contactService.addImpotToContact(contactId, impotId);
        return ResponseEntity.ok(updatedContact);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) {
        try {
            ContactDTO contactDTO = contactService.getContactById(id);
            return ResponseEntity.ok(contactDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(
            @PathVariable Long id,
            @RequestBody ContactDTO updatedContactDTO
    ) {
        try {
            ContactDTO updatedContact = contactService.updateContact(id, updatedContactDTO);
            return ResponseEntity.ok(updatedContact);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContactDTO> patchContact(
            @PathVariable Long id,
            @RequestBody ContactDTO patchedContactDTO
    ) {
        try {
            ContactDTO patchedContact = contactService.patchContact(id, patchedContactDTO);
            return ResponseEntity.ok(patchedContact);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        try {
            boolean deleted = contactService.deleteContact(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
