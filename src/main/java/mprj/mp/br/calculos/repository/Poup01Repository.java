package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.IGPM;
import mprj.mp.br.calculos.domain.jpa.PP01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "PP01", path = "pp01")
//@Repository
public interface Poup01Repository extends JpaRepository<PP01, Long> {

    List<PP01> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_pp01 e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<PP01> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);
    @Query(value = "SELECT * from tbl_pp01 e where e.valor > :valor", nativeQuery = true)
    List<PP01> findByValorNative(@Param("valor") double valor);


    //@Query("select id from IGPM e where e.data BETWEEN :startDate AND :endDate")
    //List<IGPM> findByStartDateBetween(@DateTimeFormat(pattern = "yyyy-mm-dd")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "yyyy-mm-dd")@Param("endDate")Date endDate);

    List<PP01> findAllByOrderByIdAsc();



    //findByStartDateBetween



}