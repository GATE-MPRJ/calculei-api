package mprj.mp.br.calculos.controller;



import mprj.mp.br.calculos.domain.jpa.SALARIO;

import mprj.mp.br.calculos.domain.jpa.UFIR_RJ;
import mprj.mp.br.calculos.repository.SalarioRepository;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/SALARIO")
public class SalarioController {

    @Autowired
    private SalarioRepository salarioRepository;

    @GetMapping("/allSalario")
    public List<SALARIO> findAllByOrderByIdAsc(){
        return salarioRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/SalariofindDates")
    public HttpEntity findDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
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
        List<SALARIO> lista =   salarioRepository.findByJoinedDateBetweenNative(st,ed);
        return new HttpEntity<>(lista); // RETORNA OBJETO JSON PAGINADO

    }

    @GetMapping(value = "BetweenDates")
    public HttpEntity BetweenDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        List<SALARIO> lista =   salarioRepository.findByJoinedDateBetweenNative(st,ed);

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
