package tr.gov.gib.borc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vergi", schema = "gsths")
public class Vergi {
    @Id
    @Column(name = "vergi_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "vergi_aciklama")
    private String vergiAciklama;

    @Column(name = "vergi_durum")
    private Short vergiDurum;

}