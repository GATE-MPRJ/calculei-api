package mprj.mp.br.calculos.controller;


import mprj.mp.br.calculos.domain.jpa.PoupAntiga;
import mprj.mp.br.calculos.domain.jpa.UFIR_RJ;
import mprj.mp.br.calculos.repository.PoupAntigaRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/POUPANTIGA")
public class PoupAntigaController {

    @Autowired
    private PoupAntigaRepository poupAntigaRepository;

    @GetMapping("/allPoupA")
    public List<PoupAntiga> findAllByOrderByIdAsc(){
        return poupAntigaRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/PoupAfindDates")
    public HttpEntity findDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        Calendar c = Calendar.getInstance();
        c.setTime(ed);
        c.add(Calendar.MONTH, -1);

        Date d = c.getTime();
        ed = d;
        //String res = format.format(d);
        //new DateTime(referenceDate).minusMonths(3).toDate();

        List<PoupAntiga> lista =   poupAntigaRepository.findByJoinedDateBetweenNative(st,ed);
        return new HttpEntity<>(lista); // RETORNA OBJETO JSON PAGINADO

    }
    @GetMapping(value = "BetweenDates")
    public HttpEntity BetweenDates(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date st = formato.parse(startDate);
        Date ed = formato.parse(endDate);
        Calendar c = Calendar.getInstance();
        c.setTime(ed);
        c.add(Calendar.MONTH, -1);
        Date d = c.getTime();
        ed = d;

        List<PoupAntiga> lista =   poupAntigaRepository.findByJoinedDateBetweenNative(st,ed);

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
            float f = (float) Valor3;

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00#");
            decimalFormat.setMaximumFractionDigits(8);
            //df.format(this.saldo);
            //decimalFormat.format (Valor3 / 100);
            //Float.parseFloat(decimalFormat.format(d))
            String Val4 = String.format("%.7f", Valor3);
            obj.put("id", lista.get(i).getId());
            obj.put("nome" ,lista.get(i).getNome());
            obj.put("data", lista.get(i).getData());
            obj.put("valor", lista.get(i).getValor());
            obj.put("fator", lista.get(i).getFator());
            obj.put("acumulado",  decimalFormat.format (Valor3 ));
            jsonArray.put(obj);
            System.out.println(obj);
        }
        System.out.println(jsonArray);
        obj1.put("content", jsonArray);

        return new HttpEntity<>(obj1.toString());
    }







}
