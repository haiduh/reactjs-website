package com.example.contactus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContactUsController {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @PostMapping("/contactus")
    public ResponseEntity<String> submitContactForm(@RequestBody ContactUsDto contactUsDto) {
        try {
            ContactUs contactUs = new ContactUs();
            contactUs.setName(contactUsDto.getName());
            contactUs.setEmail(contactUsDto.getEmail());
            contactUs.setMessage(contactUsDto.getMessage());
            contactUsRepository.save(contactUs);
            return ResponseEntity.status(HttpStatus.CREATED).body("Data submitted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit data");
        }
    }

    //only for testing
    @GetMapping("/contactus/{id}")
    public ResponseEntity<ContactUs> getContact(@PathVariable Long id) {
        Optional<ContactUs> contactUsOptional = contactUsRepository.findById(id);
        if (contactUsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        }
        return ResponseEntity.ok(contactUsOptional.get()); // Return contact information
    }

    //only for testing
    @DeleteMapping("/contactus/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        try {
            if (contactUsRepository.existsById(id)) {
                contactUsRepository.deleteById(id);
                return ResponseEntity.ok().body("Data has been deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete data");
        }
    }
}
