package uk.co.dajohnston.leaguetracker.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

import org.junit.Test;
import uk.co.dajohnston.leaguetracker.model.Association;

public class AssociationControllerTest {

    @Test
    public void shouldGetDefaultAssociation() {
        AssociationController controller = new AssociationController();
        Association association = controller.getAssociation();
        assertThat(association, hasProperty("name", equalTo("Scottish Football Association")));
    }
}
