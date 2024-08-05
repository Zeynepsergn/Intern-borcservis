package tr.gov.gib.borc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "iade_emanet", schema = "gsths")
public class IadeEmanet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iade_emanet_id_gen")
    @SequenceGenerator(name = "iade_emanet_id_gen", sequenceName = "iade_emanet_iade_emanet_id_seq", allocationSize = 1)
    @Column(name = "iade_emanet_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "odeme_id", nullable = false)
    private Odeme odeme;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iade_talep_id", nullable = false)
    private IadeTalep iadeTalep;

    @NotNull
    @Column(name = "iade_tutar", nullable = false)
    private BigDecimal iadeTutar;

    @NotNull
    @Column(name = "optime", nullable = false)
    private OffsetDateTime optime;

    @NotNull
    @Column(name = "iade_emanet_durum", nullable = false)
    private Integer iadeEmanetDurum;

}