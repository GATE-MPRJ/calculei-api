package mprj.mp.br.calculos.repository;

import mprj.mp.br.calculos.domain.jpa.PoupAntiga;
import mprj.mp.br.calculos.domain.jpa.PoupNova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "PoupNova", path = "poupNova")
//@Repository
public interface PoupNovaRepository extends JpaRepository<PoupNova, Long> {

    List<PoupNova> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_poupanca_nova e where e.data BETWEEN :startDate and :endDate and e.valor notnull order by e.data", nativeQuery = true)
    List<PoupNova> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                                   @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);


    @Query(value = "SELECT * from tbl_poupanca_nova e where e.data = :startDate", nativeQuery = true)
    List<PoupNova> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);


    @Query(value = "SELECT * from tbl_poupanca_nova e where e.valor > :valor", nativeQuery = true)
    List<PoupNova> findByValorNative(@Param("valor") double valor);


    @Query(value = "SELECT * from tbl_poupanca_nova e where e.data =(select distinct (max(data)) from tbl_poupanca_nova)", nativeQuery = true)
    List<PoupNova> findByLastUpdate();

    List<PoupNova> findAllByOrderByIdAsc();



    //findByStartDateBetween



}
