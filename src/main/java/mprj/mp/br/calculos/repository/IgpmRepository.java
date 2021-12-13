package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.IGPM;
import org.hibernate.annotations.Table;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource(collectionResourceRel = "IGPM", path = "igpms")
//@Repository
public interface IgpmRepository extends CrudRepository<IGPM, Long> {

    List<IGPM> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_igpm e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IGPM> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);


    @Query(value = "SELECT * from tbl_igpm e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IGPM> findBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_igpm e where e.data = :startDate", nativeQuery = true)
    List<IGPM> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_igpm e where e.valor > :valor", nativeQuery = true)
    List<IGPM> findByValorNative(@Param("valor") double valor);

/*
    @Query("select id from tbl_igpm e where e.data BETWEEN :startDate AND :endDate")
    List<IGPM> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);
*/


    @Query(value = "SELECT * from tbl_igpm e where e.data =(select distinct (max(data)) from tbl_igpm)", nativeQuery = true)
    List<IGPM> findByLastUpdate();

    List<IGPM> findAllByOrderByIdAsc();




    //findByStartDateBetween



}
