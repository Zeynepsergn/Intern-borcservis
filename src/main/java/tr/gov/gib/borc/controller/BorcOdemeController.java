package tr.gov.gib.borc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.gov.gib.borc.object.request.BorcSorguRequest;
import tr.gov.gib.borc.object.response.BorcSorguReponse;
import tr.gov.gib.borc.service.BorcOdemeService;
import tr.gov.gib.gibcore.object.request.GibRequest;
import tr.gov.gib.gibcore.object.response.GibResponse;

import java.util.List;

@RestController
public class BorcOdemeController {

    private final BorcOdemeService borcOdemeService;

    public BorcOdemeController(BorcOdemeService borcOdemeService) {
        this.borcOdemeService = borcOdemeService;
    }

    @PostMapping("/borc-ode")
    public ResponseEntity<GibResponse> tumBorcSorgula(@RequestBody GibRequest<BorcSorguReponse> request) {
        GibResponse odenenBorc = borcOdemeService.odemeYonlendir(request);
        return new ResponseEntity<>(odenenBorc, HttpStatus.OK);
    }
}
