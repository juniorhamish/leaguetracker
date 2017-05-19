package uk.co.dajohnston.leaguetracker;

public class Dave {
    public int doIt(boolean branch) {
        if (branch) {
            return doIt(false);
        }
        return 1;
    }
}
