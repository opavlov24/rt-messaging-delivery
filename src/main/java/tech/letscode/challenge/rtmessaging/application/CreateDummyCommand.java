package tech.letscode.challenge.rtmessaging.application;

import lombok.Value;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@Value
public class CreateDummyCommand implements Command //for Rabbit
{
    private String id;

    private String message;
}