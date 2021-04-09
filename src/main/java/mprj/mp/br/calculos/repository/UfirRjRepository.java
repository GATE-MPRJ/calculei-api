package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.TR;
import mprj.mp.br.calculos.domain.jpa.UFIR_RJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "UFIR_RJ", path = "ufir_rj")
//@Repository

public interface UfirRjRepository extends CrudRepository<UFIR_RJ, Long> {

    List<TR> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_ufir_rj e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)

    List<UFIR_RJ> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);


    @Query(value = "SELECT * from tbl_ufir_rj e where e.valor > :valor", nativeQuery = true)
    List<TR> findByValorNative(@Param("valor") double valor);

    @Query("select id from UFIR_RJ e where e.data BETWEEN :startDate AND :endDate")
    List<UFIR_RJ> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);

    List<UFIR_RJ> findAllByOrderByIdAsc();







}
