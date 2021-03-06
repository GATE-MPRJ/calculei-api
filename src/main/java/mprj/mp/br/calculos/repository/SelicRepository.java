package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.SALARIO;
import mprj.mp.br.calculos.domain.jpa.SELIC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "SELIC", path = "selic")
//@Repository
public interface SelicRepository extends JpaRepository<SELIC, Long> {

    List<SELIC> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_selic e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)

    List<SELIC> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_selic e where e.data = :startDate", nativeQuery = true)
    List<SELIC> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);


    @Query(value = "SELECT * from tbl_selic e where e.valor > :valor", nativeQuery = true)
    List<SELIC> findByValorNative(@Param("valor") double valor);




    @Query(value = "SELECT * from tbl_selic e where e.data =(select distinct (max(data)) from tbl_selic)", nativeQuery = true)
    List<SELIC> findByLastUpdate();


    List<SELIC> findAllByOrderByIdAsc();







}
