package uk.co.dajohnston.leaguetracker.model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class AssociationTest {

    @Test
    public void shouldSetShortName() {
        Association association = new Association();
        association.setShortName("SFA");
        assertThat(association.getShortName(), is("SFA"));
    }
}