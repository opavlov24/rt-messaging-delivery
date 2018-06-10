package tech.letscode.challenge.rtmessaging.application;

import java.util.UUID;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import tech.letscode.challenge.rtmessaging.domain.model.Dummy;
import tech.letscode.challenge.rtmessaging.domain.model.DummyRepository;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class DummyApplicationServiceTest
{
    @Test
    public void shouldCreateDummyObject()
    {
        //given
        CommandBus commandBus = Mockito.mock(CommandBus.class);
        WebSocketNotifier webSocketNotifier = Mockito.mock(WebSocketNotifier.class);
        DummyRepository dummyRepository = Mockito.mock(DummyRepository.class);
        DummyApplicationService service = new DummyApplicationService(dummyRepository, commandBus, webSocketNotifier);

        //when
        service.createDummy("Some message");

        //then
        Mockito.verify(commandBus).put(Matchers.any(Command.class));
        Mockito.verify(webSocketNotifier).notify(Matchers.eq("/topic/dummies"), Matchers.anyString());
    }

    @Test
    public void shouldPersistDummyObject()
    {
        //given
        CommandBus commandBus = Mockito.mock(CommandBus.class);
        WebSocketNotifier webSocketNotifier = Mockito.mock(WebSocketNotifier.class);
        DummyRepository dummyRepository = Mockito.mock(DummyRepository.class);
        DummyApplicationService service = new DummyApplicationService(dummyRepository, commandBus, webSocketNotifier);

        //when
        service.persistDummy(new CreateDummyCommand(UUID.randomUUID().toString(), "Some message"));

        //then
        Mockito.verify(dummyRepository).save(Matchers.any(Dummy.class));
    }


}