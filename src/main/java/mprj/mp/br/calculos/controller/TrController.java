package mprj.mp.br.calculos.controller;


import mprj.mp.br.calculos.domain.jpa.SELIC;
import mprj.mp.br.calculos.domain.jpa.TR;
import mprj.mp.br.calculos.repository.SelicRepository;
import mprj.mp.br.calculos.repository.TrRepository;
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
@RequestMapping("/TR")

public class TrController {

    @Autowired
    private TrRepository trRepository;

    @GetMapping("/allTr")
    public List<TR> findAllByOrderByIdAsc(){
        return trRepository.findAllByOrderByIdAsc();
    }

    @GetMapping(value = "BetweenDates")
    public HttpEntity BetweenDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        List<TR> lista =   trRepository.findByJoinedDateBetweenNative(st,ed);

        // Abaixo é igual em todos o controllers
        double Valor3 = 0.0 ;
        double valorJuros = 0.0;
        JSONArray jsonArray = new JSONArray();
        JSONObject obj1 = new JSONObject();
        for(int i = 0 ; i < lista.size(); i++){
            JSONObject obj = new JSONObject();
            if(i <= 0){
                Valor3 = lista.get(i).getFator();
            } else {
                Valor3 = Valor3 * lista.get(i).getFator();
            }
            obj.put("id", lista.get(i).getId());
            obj.put("nome" ,lista.get(i).getNome());
            obj.put("data", lista.get(i).getData());
            obj.put("valor", lista.get(i).getValor());
            obj.put("fator", lista.get(i).getFator());
            obj.put("acumulado", Valor3);
            jsonArray.put(obj);
            System.out.println(obj);
        }
        System.out.println(jsonArray);
        obj1.put("content", jsonArray);


        return new HttpEntity<>(obj1.toString());



        //return new HttpEntity<>(lista); // RETORNA OBJETO JSON PAGINADO

    }







}
