package com.libms.libms.service;

import com.libms.libms.entity.Patron;
import com.libms.libms.utils.PatronNotFoundException;
import com.libms.libms.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }
    public List<Patron> findAllPatrons() {
        return patronRepository.findAll();
    }
    public Optional<Patron> findPatron(Long id) {
        return patronRepository.findById(id);
    }
    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }
    public Patron updatePatron(Long id, Patron patronDetails) throws PatronNotFoundException {
        Optional<Patron> optionalPatron= patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron existingPatron = optionalPatron.get();
            existingPatron.setName(patronDetails.getName());
            existingPatron.setPhone(patronDetails.getPhone());
            existingPatron.setEmail(patronDetails.getEmail());
            return patronRepository.save(existingPatron);
        }else {
            throw new PatronNotFoundException("Patron not found with id: " + id);
        }

    }
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
