package mprj.mp.br.calculos.controller;

import mprj.mp.br.calculos.domain.jpa.*;
import mprj.mp.br.calculos.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
@RestController
@RequestMapping("/INDICES")
public class IndicesController {


    @Autowired
    private PoupNovaRepository poupNovaRpository; //

    @Autowired
    private PoupAntigaRepository poupAntigaRepository; //

    @Autowired
    private cdiRepository cdiRepository; //

    @Autowired
    private IgpmRepository igpmRepository; //

    @Autowired
    private IndicesTjFazRepository indicesTjFazRepository;

    @Autowired
    private ipcaeRepository ipcaeRepository; //

    @Autowired
    private SalarioRepository salarioRepository; //

    @Autowired
    private IndicesTjRepository indicesTjRepository; //

    @Autowired
    private SelicRepository selicRepository;//

    @Autowired
    private TrRepository trRepository; //

    @GetMapping(value = "/tableIndices")
    public HttpEntity Get() throws ParseException {
        List<PoupNova> listapn =   poupNovaRpository.findByLastUpdate();
        List<PoupAntiga> listapa =   poupAntigaRepository.findByLastUpdate();
        List<SELIC> listasl =   selicRepository.findByLastUpdate();
        List<TR> listatr =   trRepository.findByLastUpdate();
        List<IPCAE> listaIP =   ipcaeRepository.findByLastUpdate();
        List<CDI> listaCD =   cdiRepository.findByLastUpdate();
        List<IGPM> listaIG =   igpmRepository.findByLastUpdate();
        List<INDICE_TJ_FAZ> listaTJF =   indicesTjFazRepository.findByLastUpdate();
        List<INDICE_TJ> listaTJ =   indicesTjRepository.findByLastUpdate();
        List<SALARIO> listaSAL =   salarioRepository.findByLastUpdate();

        JSONArray jsonArray = new JSONArray();
        JSONObject obj1 = new JSONObject();
        jsonArray.put(listapn);
        jsonArray.put(listapa);
        jsonArray.put(listasl);
        jsonArray.put(listatr);
        jsonArray.put(listaIP);
        jsonArray.put(listaCD);
        jsonArray.put(listaIG);
        jsonArray.put(listaTJF);
        jsonArray.put(listaTJ);
        jsonArray.put(listaSAL);

        //obj1.put("Poupança Nova", lista);
        obj1.put("Indices", jsonArray);

        //
        return new HttpEntity<>(obj1.toString());
    }

}
