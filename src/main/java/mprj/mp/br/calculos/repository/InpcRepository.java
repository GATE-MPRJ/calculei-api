package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.IGPM;
import mprj.mp.br.calculos.domain.jpa.INPC;
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
@RepositoryRestResource(collectionResourceRel = "INPC", path = "inpc")
public interface InpcRepository extends CrudRepository<INPC, Long> {

    List<INPC> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_inpc e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<INPC> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_inpc e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<INPC> findBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                           @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_inpc e where e.data = :startDate", nativeQuery = true)
    List<INPC> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_inpc e where e.valor > :valor", nativeQuery = true)
    List<INPC> findByValorNative(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_igpm e where e.data =(select distinct (max(data)) from tbl_igpm)", nativeQuery = true)
    List<INPC> findByLastUpdate();

    List<INPC> findAllByOrderByIdAsc();

}
