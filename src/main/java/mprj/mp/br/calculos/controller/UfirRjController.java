package mprj.mp.br.calculos.controller;


import mprj.mp.br.calculos.domain.jpa.TR;
import mprj.mp.br.calculos.domain.jpa.UFIR_RJ;
import mprj.mp.br.calculos.repository.TrRepository;
import mprj.mp.br.calculos.repository.UfirRjRepository;
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

public class UfirRjController {

    @Autowired
    private UfirRjRepository ufirRjRepository;

    @GetMapping("/allUfirRj")
    public List<UFIR_RJ> findAllByOrderByIdAsc(){
        return ufirRjRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/UfirRjfindDates")
    public HttpEntity findDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        List<UFIR_RJ> lista =   ufirRjRepository.findByJoinedDateBetweenNative(st,ed);
        return new HttpEntity<>(lista); // RETORNA OBJETO JSON PAGINADO

    }







}