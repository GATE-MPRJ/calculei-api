package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.IGPM;
import mprj.mp.br.calculos.domain.jpa.PoupAntiga;
import mprj.mp.br.calculos.domain.jpa.PoupNova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "PP01", path = "pp01")
//@Repository
public interface PoupAntigaRepository extends JpaRepository<PoupAntiga, Long> {

    List<PoupAntiga> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_poupanca_antiga e where e.data BETWEEN :startDate and :endDate order by e.data asc", nativeQuery = true)
    List<PoupAntiga> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                                   @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);



    @Query(value = "SELECT * from tbl_poupanca_antiga e where e.data = :startDate", nativeQuery = true)
    List<PoupAntiga> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_poupanca_antiga e where e.valor > :valor", nativeQuery = true)
    List<PoupAntiga> findByValorNative(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_poupanca_antiga e where e.data =(select distinct (max(data)) from tbl_poupanca_antiga)", nativeQuery = true)
    List<PoupAntiga> findByLastUpdate();


    //@Query("select id from IGPM e where e.data BETWEEN :startDate AND :endDate")
    //List<IGPM> findByStartDateBetween(@DateTimeFormat(pattern = "yyyy-mm-dd")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "yyyy-mm-dd")@Param("endDate")Date endDate);

    List<PoupAntiga> findAllByOrderByIdAsc();



    //findByStartDateBetween



}
