package tech.letscode.challenge.rtmessaging.port.adapter.bus;

import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tech.letscode.challenge.rtmessaging.application.Command;
import tech.letscode.challenge.rtmessaging.application.CommandBus;

import static tech.letscode.challenge.rtmessaging.port.adapter.bus.ExchangeConfiguration.COMMAND_PROCESSING_EXCHANGE;
import static tech.letscode.challenge.rtmessaging.port.adapter.bus.ExchangeConfiguration.CREATE_DUMMY_ROUTING_KEY;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@Component
@RequiredArgsConstructor
public class RabbitCommandBus implements CommandBus
{
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void put(@Nonnull Command command)
    {
        this.rabbitTemplate.convertAndSend(COMMAND_PROCESSING_EXCHANGE, CREATE_DUMMY_ROUTING_KEY, command);
    }
}