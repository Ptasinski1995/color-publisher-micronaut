package mptasinski.co.brick.task.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Single;
import mptasinski.co.brick.task.api.model.Color;
import mptasinski.co.brick.task.api.model.ColorRequest;
import mptasinski.co.brick.task.api.model.ColorResponse;
import mptasinski.co.brick.task.service.ColorService;

import javax.inject.Inject;

@Controller
public class ColorsController {

    private final ColorService colorService;

    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @Post(value = "/publish")
    public Single<HttpResponse<ColorResponse>> publish(@Body Flowable<Color> colors) {
        colorService.processColors(colors);

       return Single.just(HttpResponse.ok(new ColorResponse()));

    }
}
