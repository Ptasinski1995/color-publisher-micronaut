package mptasinski.co.brick.task.messaging;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import mptasinski.co.brick.task.dto.ColorMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(notEnv = Environment.TEST)
@RabbitListener
public class ColorListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(ColorListener.class);

    @Queue("colors")
    public void receive(ColorMessageDto colorMessageDto) {
        LOGGER.info("Received color message from the queue, color={}", colorMessageDto.getColor());
    }
}
