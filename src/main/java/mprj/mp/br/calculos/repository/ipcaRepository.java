package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.CDI;

import mprj.mp.br.calculos.domain.jpa.IPCA;
import mprj.mp.br.calculos.domain.jpa.IPCA;
import mprj.mp.br.calculos.domain.jpa.IPCA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "IPCA", path = "ipca")
public interface ipcaRepository extends JpaRepository<IPCA, Long> {

    List<IPCA> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_ipca e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IPCA> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);


    @Query(value = "SELECT * from tbl_ipca e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IPCA> findBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                           @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_ipca e where e.data = :startDate", nativeQuery = true)
    List<IPCA> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_ipca e where e.valor > :valor", nativeQuery = true)
    List<IPCA> findByValorNative(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_ipca e where e.data =(select distinct (max(data)) from tbl_ipca)", nativeQuery = true)
    List<IPCA> findByLastUpdate();

    List<IPCA> findAllByOrderByIdAsc();



}

