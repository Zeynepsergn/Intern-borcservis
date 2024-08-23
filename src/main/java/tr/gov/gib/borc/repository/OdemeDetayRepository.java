package tr.gov.gib.borc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.gov.gib.borc.entity.OdemeDetay;

public interface OdemeDetayRepository extends JpaRepository<OdemeDetay, Integer> {

    @Query("""
        select od from OdemeDetay od 
        join fetch od.odeme o 
        join fetch od.odeme.mukellefBorc b 
        where od.odeme.id= :id
    """)
    OdemeDetay findOdenenDetayById(Integer id);
}
