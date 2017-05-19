package uk.co.dajohnston.leaguetracker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.dajohnston.leaguetracker.model.Association;

@RestController
public class AssociationController {

    @RequestMapping("/association")
    public Association getAssociation() {
        return new Association("Scottish Football Association");
    }
}
