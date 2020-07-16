package mptasinski.co.brick.task.messaging;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import mptasinski.co.brick.task.dto.ColorMessageDto;

@RabbitClient
public interface ColorRabbitClient {

    @Binding("colors")
    void send(ColorMessageDto colorMessageDto);
}
