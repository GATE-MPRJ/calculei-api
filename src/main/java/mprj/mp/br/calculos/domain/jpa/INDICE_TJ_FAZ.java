package mprj.mp.br.calculos.domain.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tj_l11960")
public class INDICE_TJ_FAZ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Nome")
    private String Nome;

    @Column(name = "fator")
    private double fator;

    @Column(name = "data")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date data;

    public long getId(){return id;}
    public void setId(long id){this.id = id;}

    public Date getData() {return data;}
    public void  setData(Date data){ this.data = data;}
    public double getFator(){return fator;}
    public void setFator(double fator) { this.fator = fator;}
    public String getNome() { return Nome;}
    public void setNome(String Nome){this.Nome = Nome;}

    @Override
    public String toString() {
        return "[id=" + id + ", nome=" + Nome + ", fator=" + fator + ",  data=" + data + "]";
    }
}
