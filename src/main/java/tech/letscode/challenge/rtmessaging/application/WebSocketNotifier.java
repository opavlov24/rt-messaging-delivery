package tech.letscode.challenge.rtmessaging.application;

import javax.annotation.Nonnull;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface WebSocketNotifier
{
    void notify(@Nonnull String destination, @Nonnull Object object);
}
