package uk.co.dajohnston.leaguetracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DaveTest {

    @Test
    public void foo() {
        Dave dave = new Dave();
        int result = dave.doIt(false);
        assertThat(result, is(1));
    }
}
