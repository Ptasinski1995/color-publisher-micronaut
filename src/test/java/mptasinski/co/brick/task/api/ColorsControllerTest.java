package mptasinski.co.brick.task.api;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import mptasinski.co.brick.task.api.model.Color;
import mptasinski.co.brick.task.api.model.ColorRequest;
import mptasinski.co.brick.task.api.model.ColorResponse;
import mptasinski.co.brick.task.service.ColorService;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@MicronautTest
class ColorsControllerTest {

    @Inject
    ColorsController colorsController;

    @Inject
    ColorService colorService;

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void testPublishColors() {
        //for
        HttpRequest<List<Color>> httpRequest = HttpRequest.POST("/publish", colorRequest()).accept(MediaType.APPLICATION_JSON_TYPE);

        //when
        HttpResponse<ColorResponse> response = client.toBlocking().exchange(httpRequest, ColorResponse.class);


        //then
        assertEquals(HttpStatus.OK, response.getStatus());
        assertTrue(response.getBody().get().isPublished());
    }

    private List<Color> colorRequest() {
        return Collections.singletonList(new Color("255,0,0", true));
    }


    @MockBean(ColorService.class)
    ColorService colorService() {
        return mock(ColorService.class);
    }
}