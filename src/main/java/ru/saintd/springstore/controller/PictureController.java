package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.saintd.springstore.controller.repr.PictureRepr;
import ru.saintd.springstore.service.ServiceInterfaces.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;
    //TODO раскидать админские и юзерские методы

    @GetMapping("/picture/{pictureid}")
    public void getPicture(@PathVariable("pictureid") Long pictureId,
                           HttpServletResponse response) throws IOException {
        PictureRepr pictureRepr = pictureService.findById(pictureId);
        if (pictureRepr != null) {
            response.setContentType(pictureRepr.getContentType());
            response.getOutputStream().write(pictureRepr.getPictureData().getData());
        }
    }

    @PostMapping("/picture/delete/{pictureid}")
    public String deletePicture(@PathVariable("pictureid") Long pictureId,
                                @RequestParam String pageUrl) {
        pictureService.deleteById(pictureId);
        return "redirect:" + pageUrl;
    }
}
