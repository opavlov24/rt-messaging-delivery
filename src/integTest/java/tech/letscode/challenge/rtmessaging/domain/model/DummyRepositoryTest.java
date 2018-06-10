package tech.letscode.challenge.rtmessaging.domain.model;

import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class DummyRepositoryTest
{
    @Autowired
    private DummyRepository dummyRepository;

    @Test
    public void shouldSaveAndRestoreDummy()
    {
        //given
        String id = UUID.randomUUID().toString();
        Dummy dummy = new Dummy(id, "message");

        //when
        this.dummyRepository.save(dummy);

        //then
        Dummy loaded = this.dummyRepository.findOne(id);
        assertNotNull(loaded);
        assertEquals(dummy.getId(), loaded.getId());
        assertEquals(dummy.getMessage(), loaded.getMessage());
    }
}
