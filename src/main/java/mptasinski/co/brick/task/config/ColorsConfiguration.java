package mptasinski.co.brick.task.config;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.micronaut.context.annotation.ConfigurationProperties;
import mptasinski.co.brick.task.api.model.Color;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@ConfigurationProperties("colors")
public class ColorsConfiguration {

    private Map<String, String> mapping = new HashMap<>();
    private BiMap<String, String> colorsBiMap;

    @PostConstruct
    public void setUp() {
        colorsBiMap = HashBiMap.create(mapping).inverse();
    }

    public Optional<String> getColorName(Color color) {
        return Optional.ofNullable(colorsBiMap.get(color.getColor()));
    }
}
