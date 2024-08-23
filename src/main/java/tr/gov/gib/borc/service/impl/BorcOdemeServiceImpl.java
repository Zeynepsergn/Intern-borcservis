package tr.gov.gib.borc.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.gov.gib.borc.entity.MukellefBorc;
import tr.gov.gib.borc.entity.Odeme;
import tr.gov.gib.borc.object.response.BorcSorguReponse;
import tr.gov.gib.borc.repository.MukellefBorcRepository;
import tr.gov.gib.borc.service.BorcOdemeService;
import tr.gov.gib.gibcore.exception.GibException;
import tr.gov.gib.gibcore.object.request.GibRequest;
import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.util.ServiceMessage;

@Service("BorcOdemeService")
public class BorcOdemeServiceImpl implements BorcOdemeService {

    private final MukellefBorcRepository borcRepository;
    private RestTemplate restTemplate = new RestTemplate();

    public BorcOdemeServiceImpl(MukellefBorcRepository borcRepository) {
        this.borcRepository = borcRepository;
    }

    @Override
    public GibResponse<Odeme> odemeYonlendir(GibRequest<BorcSorguReponse> request) {
        BorcSorguReponse sorguRequest = (BorcSorguReponse) request.getData();
        MukellefBorc borc = borcRepository.findById(sorguRequest.getBorcId()).orElse(null);

        if(borc == null) {
            throw new GibException(ServiceMessage.NO_OK,"Borç bilgisi bulunamadı.");
        }

        if(borc.getBorcDurum().equals((short)2)){
            throw new GibException(ServiceMessage.NO_OK,"Borç kapatılmış. Ödenebilir durumda değil!");
        }

        HttpEntity<GibRequest<BorcSorguReponse>> requestEntity = new HttpEntity<>(request);
        GibResponse response = restTemplate
                .postForObject("http://localhost:7070/odeme-server/odemeYap", requestEntity, GibResponse.class
                );

        return response;
    }
}
