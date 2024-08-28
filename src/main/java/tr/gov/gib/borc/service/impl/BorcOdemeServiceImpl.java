package tr.gov.gib.borc.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.gov.gib.borc.client.OdemeFeignClient;
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
    private final OdemeFeignClient odemeClient;
    private RestTemplate restTemplate = new RestTemplate();

    public BorcOdemeServiceImpl(MukellefBorcRepository borcRepository, OdemeFeignClient odemeClient) {
        this.borcRepository = borcRepository;
        this.odemeClient = odemeClient;
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

        GibResponse<Odeme> response = odemeClient.odemeYap(request);

        return response;
    }
}
