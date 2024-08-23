package tr.gov.gib.borc.service;

import tr.gov.gib.borc.entity.Odeme;
import tr.gov.gib.borc.object.response.BorcSorguReponse;
import tr.gov.gib.gibcore.object.request.GibRequest;
import tr.gov.gib.gibcore.object.response.GibResponse;

public interface BorcOdemeService {

    GibResponse<Odeme> odemeYonlendir(GibRequest<BorcSorguReponse> request);
}
