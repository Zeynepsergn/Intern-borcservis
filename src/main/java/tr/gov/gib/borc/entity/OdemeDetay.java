package tr.gov.gib.borc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "odeme_detay", schema = "gsths")
public class OdemeDetay {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odeme_detay_id_gen")
    @SequenceGenerator(name = "odeme_detay_id_gen", sequenceName = "odeme_detay_odeme_detay_id_seq", allocationSize = 1)
    @Column(name = "odeme_detay_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odeme_id")
    private Odeme odeme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukellef_kullanici_id")
    private MukellefKullanici mukellefKullanici;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vergi_id")
    private Vergi vergi;

    @Size(max = 20)
    @Column(name = "oid", length = 20)
    private String oid;

    @Size(max = 100)
    @Column(name = "aciklama", length = 100)
    private String aciklama;

    @Column(name = "odenen_borc_miktari")
    private BigDecimal odenenBorcMiktari;

    @Column(name = "odeme_detay_durum")
    private Short odemeDetayDurum;

    @Column(name = "iade_zamani")
    private OffsetDateTime iadeZamani;

    @Column(name = "optime")
    private OffsetDateTime optime;

}