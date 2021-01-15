package mprj.mp.br.calculos.repository;


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

    /* @DateTimeFormat(iso = ISO.DATE
    List<IGPM> getAllBetweenDates(@Param("startDate") java.sql.Date startDate , @Param("endDate") java.sql.Date endDate);
             @DateTimeFormat(pattern = "yyyy-mm-dd")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "yyyy-mm-dd")@Param("endDate")Date endDate);

             */


    @Query(value = "SELECT * from tbl_tr e where e.valor > :valor", nativeQuery = true)
    List<TR> findByValorNative(@Param("valor") double valor);


    @Query("select id from TR e where e.data BETWEEN :startDate AND :endDate")
    List<TR> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);

    List<TR> findAllByOrderByIdAsc();







}
