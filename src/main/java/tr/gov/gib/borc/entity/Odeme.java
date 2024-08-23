package tr.gov.gib.borc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "odeme", schema = "gsths")
public class Odeme {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odeme_id_gen")
    @SequenceGenerator(name = "odeme_id_gen", sequenceName = "odeme_odeme_id_seq", allocationSize = 1)
    @Column(name = "odeme_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukellef_borc_id")
    private MukellefBorc mukellefBorc;

    @Column(name = "optime")
    private OffsetDateTime optime;

    @Column(name = "odeme_durum")
    private Short odemeDurum;

    @Column(name = "vergi_id")
    private Integer vergiId;

    @Column(name = "mukellef_kullanici_id")
    private Long mukellefKullaniciId;

    @OneToOne(mappedBy = "odeme")
    private OdemeDetay odemeDetay;

}