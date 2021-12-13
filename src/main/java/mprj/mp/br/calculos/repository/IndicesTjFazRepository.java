package mprj.mp.br.calculos.repository;


import mprj.mp.br.calculos.domain.jpa.INDICE_TJ_FAZ;
import mprj.mp.br.calculos.domain.jpa.TR;
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


    @Query(value = "SELECT * from tbl_fator_correcao_tjrj e where e.data BETWEEN :startDate and :endDate", nativeQuery = true)
    List<INDICE_TJ_FAZ> findByJoinedDateBetweenNative(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date endDate);

    @Query(value = "SELECT * from tbl_fator_correcao_tjrj e where e.data = :startDate", nativeQuery = true)
    List<INDICE_TJ_FAZ> findByDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date startDate);

    @Query(value = "SELECT * from tbl_fator_correcao_tjrj e where e.data =(select distinct (max(data)) from tbl_fator_correcao_tjrj)", nativeQuery = true)
    List<INDICE_TJ_FAZ> findByLastUpdate();

    List<INDICE_TJ_FAZ> findAllByOrderByIdAsc();







}
