package medusa.repositories;

import org.springframework.data.repository.CrudRepository;

import medusa.models.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
