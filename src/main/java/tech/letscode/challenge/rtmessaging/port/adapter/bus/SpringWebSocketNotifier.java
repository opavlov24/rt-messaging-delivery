package tech.letscode.challenge.rtmessaging.port.adapter.bus;

import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import tech.letscode.challenge.rtmessaging.application.WebSocketNotifier;
import tech.letscode.challenge.rtmessaging.shared.Args;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@Component
@RequiredArgsConstructor
public class SpringWebSocketNotifier implements WebSocketNotifier
{
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void notify(@Nonnull String destination, @Nonnull Object object)
    {
        Args.notNull(destination, "destination is required.");
        Args.notNull(object, "object is required.");

        this.simpMessageSendingOperations.convertAndSend(destination, object);
    }
}