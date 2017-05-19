package uk.co.dajohnston.leaguetracker.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.dajohnston.leaguetracker.model.Association;

public interface AssociationRepository extends CrudRepository<Association, Long> {
}
