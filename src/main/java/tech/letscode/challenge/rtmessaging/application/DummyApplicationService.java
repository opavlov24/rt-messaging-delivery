package tech.letscode.challenge.rtmessaging.application;

import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.letscode.challenge.rtmessaging.domain.model.Dummy;
import tech.letscode.challenge.rtmessaging.domain.model.DummyRepository;
import tech.letscode.challenge.rtmessaging.shared.Args;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@RequiredArgsConstructor
@Service
public class DummyApplicationService
{
    private final DummyRepository dummyRepository;

    private final CommandBus commandBus;

    private final WebSocketNotifier webSocketNotifier;

    public void createDummy(@Nonnull String message)
    {
        Args.notNull(message, "message is required");

        CreateDummyCommand command = new CreateDummyCommand(UUID.randomUUID().toString(), message);
        this.commandBus.put(command);
        this.webSocketNotifier.notify("/topic/dummies", message);
    }

    public void persistDummy(@Nonnull CreateDummyCommand command)
    {
        Args.notNull(command, "command is required");

        Dummy dummy = new Dummy(command.getId(), command.getMessage());
        this.dummyRepository.save(dummy);
    }

    public Page<Dummy> dummies(@Nonnull Pageable pageable)
    {
        Args.notNull(pageable, "pageable is required");

        return this.dummyRepository.findAll(pageable);
    }
}
