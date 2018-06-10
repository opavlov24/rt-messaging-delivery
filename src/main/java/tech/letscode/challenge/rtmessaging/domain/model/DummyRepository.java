package tech.letscode.challenge.rtmessaging.domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface DummyRepository extends MongoRepository<Dummy, String>
{
}
