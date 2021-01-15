package mprj.mp.br.calculos.controller;



import mprj.mp.br.calculos.domain.jpa.IPCAE;
import mprj.mp.br.calculos.repository.ipcaeRepository;
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

public class ipcaeController {

    @Autowired
    private ipcaeRepository ipcaeRepository;

    @GetMapping("/allIpcae")
    public List<IPCAE> findAllByOrderByIdAsc(){
        return ipcaeRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/IPCAEfindDates")
    public HttpEntity findDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        List<IPCAE> lista =   ipcaeRepository.findByJoinedDateBetweenNative(st,ed);
        return new HttpEntity<>(lista); // RETORNA OBJETO JSON PAGINADO

    }

}
