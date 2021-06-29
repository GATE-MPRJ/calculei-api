package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.INDICE_TJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "INDICE_TJ", path = "ufir_rj")
//@Repository
public interface IndicesTjRepository extends JpaRepository<INDICE_TJ, Long> {

    //List<TR> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_ufir_rj e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<INDICE_TJ> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_ufir_rj e where e.data = :startDate", nativeQuery = true)
    List<INDICE_TJ> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    /*
    @Query("select id from tbl_ufir_rj e where e.data BETWEEN :startDate AND :endDate")
    List<INDICE_TJ> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);

     */

    List<INDICE_TJ> findAllByOrderByIdAsc();







}
