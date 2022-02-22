package mprj.mp.br.calculos.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mprj.mp.br.calculos.domain.jpa.CDI;
import mprj.mp.br.calculos.domain.jpa.IPCBR;
import mprj.mp.br.calculos.repository.ipcbrRepository;
import mprj.mp.br.calculos.repository.ipcbrRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/IPC-BR")
@Api(value = "Controller do IPC-BRRASIL" )
public class ipcbrController {

    @Autowired
    private ipcbrRepository ipcbrRepository;

    @ApiOperation(value = "LISTA TODOS DADOS DE CDI DO DB" )
    @GetMapping("/allIPCBR")
    public List<IPCBR> findAllByOrderByIdAsc(){
        return                ipcbrRepository.findAll();
    }

    @ApiOperation(value = "RETORNA JSON DE IPC-BR ENTRE DATAS" )
    @GetMapping(value = "BetweenDates")
    public HttpEntity BetweenDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        List<IPCBR> lista =   ipcbrRepository.findByJoinedDateBetweenNative(st,ed);

        double Valor3 = 0.0 ;
        JSONArray jsonArray = new JSONArray();
        JSONObject obj1 = new JSONObject();
        for(int i = 0 ; i < lista.size(); i++){
            JSONObject obj = new JSONObject();
            if(i <= 0){
                Valor3 = lista.get(i).getFator();
            } else {
                Valor3 = Valor3 * lista.get(i).getFator();
            }
            String Val4 = String.format("%.7f", Valor3);
            obj.put("id", lista.get(i).getId());
            obj.put("nome" ,lista.get(i).getNome());
            obj.put("data", lista.get(i).getData());
            obj.put("valor", lista.get(i).getValor());
            obj.put("fator", lista.get(i).getFator());
            obj.put("acumulado", Val4);
            jsonArray.put(obj);
            System.out.println(obj);
        }
        System.out.println(jsonArray);
        obj1.put("content", jsonArray);


        return new HttpEntity<>(obj1.toString());


    }



}
