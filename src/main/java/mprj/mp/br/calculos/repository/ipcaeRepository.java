package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.CDI;
import mprj.mp.br.calculos.domain.jpa.IGPM;
import mprj.mp.br.calculos.domain.jpa.IPCAE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "IPCAE", path = "ipcae")
//@Repository
public interface ipcaeRepository extends JpaRepository<IPCAE, Long> {

    List<IPCAE> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_ipcae e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IPCAE> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_ipcae e where e.data = :startDate", nativeQuery = true)
    List<IPCAE> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_ipcae e where e.valor > :valor", nativeQuery = true)
    List<IPCAE> findByValorNative(@Param("valor") double valor);



    @Query(value = "SELECT * from tbl_ipcae e where e.data =(select distinct (max(data)) from tbl_ipcae)", nativeQuery = true)
    List<IPCAE> findByLastUpdate();

    @Query(value = "SELECT * from tbl_ipcae e order by e.data asc ", nativeQuery = true)
    List<IPCAE> findOrder();


    List<IPCAE> findAllByOrderByIdAsc();







}
