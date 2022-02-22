package mprj.mp.br.calculos.repository;



import mprj.mp.br.calculos.domain.jpa.*;

import mprj.mp.br.calculos.domain.jpa.IPCBR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "IPCBR", path = "ipcbr")
public interface ipcbrRepository extends JpaRepository<IPCBR, Long> {

    List<IPCBR> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_ipc_br e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IPCBR> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                              @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);


    @Query(value = "SELECT * from tbl_ipc_br e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<IPCBR> findBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                            @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_ipc_br e where e.data = :startDate", nativeQuery = true)
    List<IPCBR> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_ipc_br e where e.valor > :valor", nativeQuery = true)
    List<IPCBR> findByValorNative(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_ipc_br e where e.data =(select distinct (max(data)) from tbl_ipc_br)", nativeQuery = true)
    List<IPCBR> findByLastUpdate();

    List<IPCBR> findAllByOrderByIdAsc();


}



