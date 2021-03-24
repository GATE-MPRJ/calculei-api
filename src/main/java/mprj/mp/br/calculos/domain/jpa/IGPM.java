package mprj.mp.br.calculos.domain.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_igpm")
public class IGPM {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id = -1;

    @Column(name = "nome")
    private String Nome;

    @Column(name = "fator")
    private double fator;

    @Column(name = "valor")
    private double valor;

    @Column(name = "data")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date data;
    /*
    @Column(name = "acumulado")
    private Double acumulado;

     */

    public long getId(){return id;}
    public double getValor(){ return  valor;}
    public void setValor(double valor){ this.valor = valor;}
    public Date getData() {return data;}
    public void  setData(Date data){ this.data = data;}
    public double getFator(){return fator;}
    public void setFator(double fator) { this.fator = fator;}
    public String getNome() { return Nome;}
    public void setNome(String Nome){this.Nome = Nome;}
    /*
    public void setAcumulado(Double acumulado){
        return acumulado;
    }

     */
    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", nome=" + Nome + ", fator=" + fator + ", valor = "  + valor + ", data=" +data+ "]";
    }
}
