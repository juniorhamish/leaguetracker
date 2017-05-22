package uk.co.dajohnston.leaguetracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Association {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String shortName;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
