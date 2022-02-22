package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.CDI;

import mprj.mp.br.calculos.domain.jpa.IGPDI;
import mprj.mp.br.calculos.domain.jpa.IGPDI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "IGPDI", path = "igpdi")
public interface igpdiRepository extends JpaRepository<IGPDI, Long> {
    List<IGPDI> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_IGPDI e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IGPDI> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);


    @Query(value = "SELECT * from tbl_IGPDI e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IGPDI> findBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                           @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_IGPDI e where e.data = :startDate", nativeQuery = true)
    List<IGPDI> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_IGPDI e where e.valor > :valor", nativeQuery = true)
    List<IGPDI> findByValorNative(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_IGPDI e where e.data =(select distinct (max(data)) from tbl_IGPDI)", nativeQuery = true)
    List<IGPDI> findByLastUpdate();

    List<IGPDI> findAllByOrderByIdAsc();



}
