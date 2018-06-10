package tech.letscode.challenge.rtmessaging.application;

import javax.annotation.Nonnull;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface CommandBus
{
    void put(@Nonnull Command payload);
}