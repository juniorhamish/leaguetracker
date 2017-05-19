package uk.co.dajohnston.leaguetracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DaveTest {

    @Test
    public void resultIs1WhenNotBranching() {
        Dave dave = new Dave();
        int result = dave.doIt(false);
        assertThat(result, is(1));
    }

    @Test
    public void resultIs2WhenBranching() {
        Dave dave = new Dave();
        int result = dave.doIt(true);
        assertThat(result, is(2));
    }
}
