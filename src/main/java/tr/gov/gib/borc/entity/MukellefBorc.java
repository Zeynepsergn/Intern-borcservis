package tr.gov.gib.borc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "mukellef_borc", schema = "gsths")
public class MukellefBorc {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mukellef_borc_id_gen")
    @SequenceGenerator(name = "mukellef_borc_id_gen", sequenceName = "mukellef_borc_mukellef_borc_id_seq", allocationSize = 1)
    @Column(name = "mukellef_borc_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vergi_id")
    private Vergi vergi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukellef_kullanici_id")
    private MukellefKullanici mukellefKullanici;

    @Column(name = "mukellef_borc", precision = 10, scale = 2)
    private BigDecimal mukellefBorc;

    @Column(name = "odenen_borc", precision = 10, scale = 2)
    private BigDecimal odenenBorc;

    @Column(name = "kalan_borc", precision = 10, scale = 2)
    private BigDecimal kalanBorc;

    @Column(name = "borc_durum")
    private Short borcDurum;

    @Column(name = "ilk_kayit")
    private OffsetDateTime ilkKayit;

    @Column(name = "optime")
    private OffsetDateTime optime;

    @OneToMany(mappedBy = "mukellefBorc")
    private Set<Odeme> odemes = new LinkedHashSet<>();

}