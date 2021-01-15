package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.SALARIO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "SALARIO", path = "salario")
//@Repository
public interface SalarioRepository extends JpaRepository<SALARIO, Long> {

    List<SALARIO> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_salario e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<SALARIO> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_salario e where e.valor > :valor", nativeQuery = true)
    List<SALARIO> findByValorNative(@Param("valor") double valor);


    @Query("select id from SALARIO e where e.data BETWEEN :startDate AND :endDate")
    List<SALARIO> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);

    List<SALARIO> findAllByOrderByIdAsc();







}
