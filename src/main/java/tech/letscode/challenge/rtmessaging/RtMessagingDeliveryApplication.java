package tech.letscode.challenge.rtmessaging;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@EnableRabbit
@EnableSpringDataWebSupport
@SpringBootApplication
public class RtMessagingDeliveryApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RtMessagingDeliveryApplication.class, args);
    }

    @Configuration
    @EnableWebSocket
    @EnableWebSocketMessageBroker
    public static class WebsocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer
    {
        @Value("${spring.rabbitmq.host}")
        private String host;

        @Value("${spring.rabbitmq.username}")
        private String clientLogin;

        @Value("${spring.rabbitmq.password}")
        private String clientPasscode;

        @Value("${spring.rabbitmq.ws.port}")
        private int port;

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry)
        {
            registry.addEndpoint("/websocket").withSockJS();
        }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry)
        {
            registry.enableStompBrokerRelay("/topic")
                    .setClientLogin(this.clientLogin)
                    .setClientPasscode(this.clientPasscode)
                    .setRelayPort(this.port)
                    .setRelayHost(this.host);
        }
    }
}
