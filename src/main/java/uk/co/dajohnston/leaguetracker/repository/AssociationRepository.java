package uk.co.dajohnston.leaguetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.co.dajohnston.leaguetracker.model.Association;

@RepositoryRestResource
public interface AssociationRepository extends MongoRepository<Association, Long> {

}
