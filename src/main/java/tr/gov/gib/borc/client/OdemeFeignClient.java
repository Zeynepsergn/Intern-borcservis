package tr.gov.gib.borc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tr.gov.gib.borc.entity.Odeme;
import tr.gov.gib.borc.object.response.BorcSorguReponse;
import tr.gov.gib.gibcore.object.request.GibRequest;
import tr.gov.gib.gibcore.object.response.GibResponse;

@FeignClient(name = "odeme-service")
public interface OdemeFeignClient {

    @PostMapping("/odemeYap")
    GibResponse<Odeme> odemeYap(@RequestBody GibRequest<BorcSorguReponse> request);
}
