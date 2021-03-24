package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.CDI;
import mprj.mp.br.calculos.domain.jpa.IGPM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "CDI", path = "cdi")
//@Repository
public interface cdiRepository extends JpaRepository<CDI, Long> {

    List<CDI> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_cdi e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)

    List<CDI> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);


    @Query(value = "SELECT * from tbl_cdi e where e.data = :startDate", nativeQuery = true)
    List<CDI> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);


    @Query(value = "SELECT * from tbl_cdi e where e.valor > :valor", nativeQuery = true)
    List<CDI> findByValorNative(@Param("valor") double valor);


    @Query("select id from IGPM e where e.data BETWEEN :startDate AND :endDate")
    List<CDI> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);

    List<CDI> findAllByOrderByIdAsc();







}
