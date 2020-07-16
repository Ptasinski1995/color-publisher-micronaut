package mptasinski.co.brick.task.messaging;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.rabbitmq.connect.ChannelInitializer;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class RabbitChannelInitializer extends ChannelInitializer {

    private static final String EXCHANGE = "mptasinski.co.brick.task";
    private static final String QUEUE = "colors";

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT, true);
        channel.queueDeclare(QUEUE, true, false, false, null);
        channel.queueBind(QUEUE, EXCHANGE, QUEUE);
    }
}
