package Processador;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;

public class Processadores {

    Looca looca = new Looca();
    Sistema sistema = new Sistema();

    private String nomeCPU;
    private String frequencia;
    private String tempoAtividade;

    public String getNomeCPU() {
        return nomeCPU = looca.getProcessador().getNome();
    }

    public void setNomeCPU(String nomeCPU) {
        this.nomeCPU = nomeCPU;
    }

    public String getFrequencia() {
        return frequencia = String.valueOf(looca.getProcessador().getFrequencia()/1e9);
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }


    public String getTempoAtividade() {
        return tempoAtividade = Conversor.formatarSegundosDecorridos(sistema.getTempoDeAtividade());
    }

    public void setTempoAtividade(String tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }
}
