package tech.letscode.challenge.rtmessaging.application;

import lombok.Value;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@Value
public class CreateDummyCommand implements Command //for Rabbit
{
    public static final String COMMAND_NAME = "CREATE_DUMMY";

    private String id;

    private String message;

    @Override
    public String commandName()
    {
        return COMMAND_NAME;
    }
}