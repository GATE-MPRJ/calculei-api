package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.IGPM;
import mprj.mp.br.calculos.domain.jpa.INDICE_TJ_FAZ;
import mprj.mp.br.calculos.domain.jpa.TR;
import mprj.mp.br.calculos.domain.jpa.UFIR_RJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "INDICE_TJ_FAS", path = "indice_tj_faz")
//@Repository
public interface IndicesTjFazRepository extends JpaRepository<INDICE_TJ_FAZ, Long> {

    List<TR> findByValor(@Param("valor") double valor);

    @Query(value = "SELECT * from tbl_fator_correcao_faz_tjrj e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)

    List<INDICE_TJ_FAZ> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_fator_correcao_faz_tjrj e where e.data = :startDate", nativeQuery = true)
    List<INDICE_TJ_FAZ> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_fator_correcao_faz_tjrj e where e.valor > :valor", nativeQuery = true)
    List<INDICE_TJ_FAZ> findByValorNative(@Param("valor") double valor);

    @Query("select id from INDICE_TJ_FAZ e where e.data BETWEEN :startDate AND :endDate")
    List<INDICE_TJ_FAZ> findByStartDateBetween(@DateTimeFormat(pattern = "dd-mm-yyyy")@Param("startDate") Date startDate, @DateTimeFormat(pattern = "dd-mm-yyyy")@Param("endDate")Date endDate);

    List<INDICE_TJ_FAZ> findAllByOrderByIdAsc();







}
