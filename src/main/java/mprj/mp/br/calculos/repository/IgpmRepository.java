package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.IGPM;
import org.hibernate.annotations.Table;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "IGPM", path = "igpm")
//@Repository
public interface IgpmRepository extends JpaRepository<IGPM, Long> {

    List<IGPM> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_igpm e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IGPM> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);
    /* @DateTimeFormat(iso = ISO.DATE
    List<IGPM> getAllBetweenDates(@Param("startDate") java.sql.Date startDate , @Param("endDate") java.sql.Date endDate);
             @DateTimeFormat(pattern = "yyyy-mm-dd")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "yyyy-mm-dd")@Param("endDate")Date endDate);

             */


    @Query(value = "SELECT * from tbl_igpm e where e.valor > :valor", nativeQuery = true)
    List<IGPM> findByValorNative(@Param("valor") double valor);


    @Query("select id from IGPM e where e.data BETWEEN :startDate AND :endDate")
    List<IGPM> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);

    List<IGPM> findAllByOrderByIdAsc();



    //findByStartDateBetween



}
