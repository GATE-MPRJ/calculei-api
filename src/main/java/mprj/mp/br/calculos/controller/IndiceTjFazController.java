package mprj.mp.br.calculos.controller;


import mprj.mp.br.calculos.domain.jpa.INDICE_TJ;
import mprj.mp.br.calculos.domain.jpa.INDICE_TJ_FAZ;
import mprj.mp.br.calculos.repository.IndicesTjFazRepository;
import mprj.mp.br.calculos.repository.IndicesTjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController

public class IndiceTjFazController {

    @Autowired
    private IndicesTjFazRepository indicesTjFazRepository;

    @GetMapping("/allIndiceTjFaz")
    public List<INDICE_TJ_FAZ> findAllByOrderByIdAsc(){
        return indicesTjFazRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/IndiceTjFazfindDates")
    public HttpEntity findDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        List<INDICE_TJ_FAZ> lista =   indicesTjFazRepository.findByJoinedDateBetweenNative(st,ed);
        return new HttpEntity<>(lista); // RETORNA OBJETO JSON PAGINADO

    }







}
