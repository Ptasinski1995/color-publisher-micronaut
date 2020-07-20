package mptasinski.co.brick.task.service;

import io.micronaut.http.HttpResponse;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import mptasinski.co.brick.task.api.model.Color;
import mptasinski.co.brick.task.api.model.ColorResponse;
import mptasinski.co.brick.task.config.ColorsConfiguration;
import mptasinski.co.brick.task.dto.ColorMessageDto;
import mptasinski.co.brick.task.mapping.ColorMessageMapper;
import mptasinski.co.brick.task.messaging.ColorRabbitClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.Optional;

@Singleton
public class ColorService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ColorService.class);

    @Inject
    private ColorsConfiguration colorsConfiguration;

    @Inject
    private ColorMessageMapper colorMessageMapper;

    @Inject
    private ColorRabbitClient colorRabbitClient;

    public Flowable<ColorMessageDto> processColors(Flowable<Color> colors) {
        return colors
                .filter(Color::isPublish)
                .map(colorsConfiguration::getColorName)
                .filter(isNameCorrect(Optional::isPresent))
                .map(Optional::get)
                .map(colorMessageMapper::mapToColorMessageDto)
                .doOnNext(colorRabbitClient::send);
    }

    private Predicate<Optional<String>> isNameCorrect(Predicate<Optional<String>> predicate) {
        return value -> {
            if (!predicate.test(value)) {
                LOGGER.warn("Color value is incorrect and won't be processed further");
                return false;
            }

            return true;
        };
    }
}
