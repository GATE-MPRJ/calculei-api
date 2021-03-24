package mprj.mp.br.calculos.domain.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_fator_correcao_tjrj")
public class INDICE_TJ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Nome")
    private String Nome;

    @Column(name = "fator")
    private double fator;

    @Column(name = "valor")
    private double valor;
    @Column(name = "data")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date data;

    public long getId(){return id;}
    public double getValor(){ return  valor;}
    public void setValor(double valor){ this.valor = valor;}
    public Date getData() {return data;}
    public void  setData(Date data){ this.data = data;}
    public double getFator(){return fator;}
    public void setFator(double fator) { this.fator = fator;}
    public String getNome() { return Nome;}
    public void setNome(String Nome){this.Nome = Nome;}

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", nome=" + Nome + ", fator=" + fator + ", valor = "  + valor + ", data=" + data + "]";
    }
}
