package mprj.mp.br.calculos.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mprj.mp.br.calculos.domain.jpa.CDI;
import mprj.mp.br.calculos.domain.jpa.IGPM;
import mprj.mp.br.calculos.domain.jpa.INPC;
import mprj.mp.br.calculos.repository.InpcRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/INPC")
@Api(value = "Controller do INPC" )
public class inpcController {

    @Autowired
    private InpcRepository inpcRepository;

    @ApiOperation(value = "LISTA TODOS DADOS DE CDI DO DB" )
    @GetMapping("/allCdi")
    public List<INPC> findAllByOrderByIdAsc(){
        return
                inpcRepository.findAllByOrderByIdAsc();
    }

    @ApiOperation(value = "RETORNA JSON DE IGP-M ENTRE DATAS" )
    @RequestMapping(value = "BetweenDates", method = RequestMethod.GET)
    public HttpEntity BetweenDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        // Calendario para pregar o Dia menor que 31
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(st);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("MM").format(calendar.getTime()));
        int stday = calendar.get(Calendar.DAY_OF_MONTH);
        String stMonth = new SimpleDateFormat("MM").format(calendar.getTime());;
        int stYear = calendar.get(Calendar.YEAR);
        if(calendar.get(Calendar.DAY_OF_MONTH) <= 31){

            st = formato.parse("01"+"-"+ stMonth +"-"+ stYear);
        }

        Date ed = formato.parse(endDate);
        System.out.println(st);
        System.out.println(ed);
        List<INPC> lista =   inpcRepository.findByJoinedDateBetweenNative(st,ed);

        // Abaixo é igual em todos o controllers
        double Valor3 = 0.00 ;
        JSONArray jsonArray = new JSONArray();
        JSONObject obj1 = new JSONObject();
        for(int i = 0 ; i < lista.size(); i++){
            JSONObject obj = new JSONObject();
            if(i <= 0){
                Valor3 = lista.get(i).getFator() * 1.00;
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

    @GetMapping("/allinpcJson")
    public HttpEntity alligpmJson() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        List<INPC> lista =   inpcRepository.findAllByOrderByIdAsc();
        return new HttpEntity<>(lista); // RETORNA OBJETO JSON PAGINADO

    }


}
