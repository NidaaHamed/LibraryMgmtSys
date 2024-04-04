package com.libms.libms.patrons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatronController {
    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }
    @GetMapping("/api/patrons")
    public List<Patron> getAllPatrons() {
        return patronService.findAllPatrons();
    }
    @GetMapping("/api/patrons/{id}")
    public Optional<Patron> getPatron(@PathVariable Long id){
        return patronService.findPatron(id);
    }
    @PostMapping("/api/patrons")
    public ResponseEntity<Patron> createPatron(@RequestBody Patron patron) {
        Patron savedPatron = patronService.savePatron(patron);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }
    @PutMapping("/api/patrons/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron patronDetails) throws PatronNotFoundException {
        Patron updatedPatron = patronService.updatePatron(id, patronDetails);
        return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
    }
    @DeleteMapping("/api/patrons/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
