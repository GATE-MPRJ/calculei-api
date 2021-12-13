package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.IGPM;
import mprj.mp.br.calculos.domain.jpa.SELIC;
import mprj.mp.br.calculos.domain.jpa.TR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "TR", path = "tr")
//@Repository
public interface TrRepository extends JpaRepository<TR, Long> {

    List<TR> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_tr e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)

    List<TR> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_tr e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<TR> findBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                           @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_tr e where e.data = :startDate", nativeQuery = true)
    List<TR> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);


    @Query(value = "SELECT * from tbl_tr e where e.valor > :valor", nativeQuery = true)
    List<TR> findByValorNative(@Param("valor") double valor);



    @Query(value = "SELECT * from tbl_tr e where e.data =(select distinct (max(data)) from tbl_tr)", nativeQuery = true)
    List<TR> findByLastUpdate();
    List<TR> findAllByOrderByIdAsc();







}
