package tech.letscode.challenge.rtmessaging.port.adapter.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tech.letscode.challenge.rtmessaging.application.CreateDummyCommand;
import tech.letscode.challenge.rtmessaging.application.DummyApplicationService;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@Component
@RequiredArgsConstructor
public class CreateDummyCommandListener
{
    private final DummyApplicationService dummyApplicationService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = ExchangeConfiguration.CREATE_DUMMY_QUEUE, durable = "true"),
            exchange = @Exchange(value = ExchangeConfiguration.COMMAND_PROCESSING_EXCHANGE, durable = "true"),
            key = ExchangeConfiguration.CREATE_DUMMY_ROUTING_KEY
    ))
    public void createDummyCommandHandler(@Payload CreateDummyCommand command)
    {
        this.dummyApplicationService.persistDummy(command);
    }
}