package uk.co.dajohnston.leaguetracker.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.co.dajohnston.leaguetracker.model.Association;

@RepositoryRestResource
public interface AssociationRepository extends PagingAndSortingRepository<Association, Long> {

    Association findByName(@Param("name") String name);
}
