package uk.co.dajohnston.leaguetracker.model;

import org.springframework.data.annotation.Id;

public class Association {

    @Id
    private long id;
    private String name;

    protected Association() {
        super();
    }

    public Association(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
