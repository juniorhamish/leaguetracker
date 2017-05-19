package uk.co.dajohnston.leaguetracker.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.dajohnston.leaguetracker.model.Association;
import uk.co.dajohnston.leaguetracker.repository.AssociationRepository;

@RestController
public class AssociationController {

    @Autowired
    private AssociationRepository associationRepository;

    @RequestMapping(value = "/associations", method = GET)
    public Iterable<Association> getAssociations() {
        return associationRepository.findAll();
    }

    @RequestMapping(value = "/associations/{id}", method = GET)
    public Association getAssociation(@PathVariable Long id) {
        return associationRepository.findOne(id);
    }

    @RequestMapping(value = "/associations/{id}", method = DELETE)
    public void deleteAssociation(@PathVariable Long id) {
        associationRepository.delete(id);
    }

    @RequestMapping(value = "/associations", method = POST)
    public Association createAssociation(@RequestBody Association association) {
        associationRepository.save(association);
        return association;
    }
}
