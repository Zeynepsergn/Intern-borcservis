package tr.gov.gib.borc.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tr.gov.gib.borc.entity.MukellefBorc;
import tr.gov.gib.borc.entity.Odeme;
import tr.gov.gib.borc.entity.OdemeDetay;
import tr.gov.gib.borc.repository.MukellefBorcRepository;
import tr.gov.gib.borc.repository.OdemeDetayRepository;
import tr.gov.gib.borc.repository.OdemeRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public class OdemeConsumer {

    @Value("${spring.rabbitmq.listener.queue}")
    private String queueName;

    private final OdemeDetayRepository odemeDetayRepository;
    private final MukellefBorcRepository mukellefBorcRepository;
    private final OdemeRepository odemeRepository;

    public OdemeConsumer(OdemeDetayRepository odemeDetayRepository, MukellefBorcRepository mukellefBorcRepository, OdemeRepository odemeRepository) {
        this.odemeDetayRepository = odemeDetayRepository;
        this.mukellefBorcRepository = mukellefBorcRepository;
        this.odemeRepository = odemeRepository;
    }

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.listener.queue}", concurrency = "${spring.rabbitmq.listener.count}")
    public void receive(Odeme message) {

        Odeme odeme = odemeRepository.findById(message.getId()).orElse(null);
        if (odeme == null) {
            throw new RuntimeException("Odeme not found. id : " + message.getId());
        }
        OdemeDetay odemeDetay = odeme.getOdemeDetay();

        MukellefBorc borc = odemeDetay.getOdeme().getMukellefBorc();
        borc.setBorcDurum((short)1);

        BigDecimal odenenTutar = odemeDetay.getOdenenBorcMiktari();
        BigDecimal eskiBorc = borc.getKalanBorc();
        BigDecimal yeniBorc = eskiBorc.subtract(odenenTutar);
        BigDecimal toplamOdenen = borc.getOdenenBorc().add(odenenTutar);

        if(toplamOdenen.compareTo(borc.getMukellefBorc()) >= 0) {
            borc.setBorcDurum((short)2);
        }

        borc.setKalanBorc(yeniBorc);
        borc.setOdenenBorc(toplamOdenen);
        borc.setOptime(OffsetDateTime.now(ZoneId.systemDefault()));
        mukellefBorcRepository.save(borc);

        odeme.setOdemeDurum((short)3);
        odeme.setOptime(OffsetDateTime.now(ZoneId.systemDefault()));
        odemeDetay.setOdeme(odeme);
        odemeRepository.save(odeme);
        odemeDetayRepository.save(odemeDetay);

    }
}
