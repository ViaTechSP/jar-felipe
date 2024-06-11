package Disco;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;

public class Disco {

    Looca looca = new Looca();

    private String velocidadeLeitura;
    private String espacoDisponivel;
    private String espacoTotal;


    public String getVelocidadeLeitura() {
        return velocidadeLeitura = Conversor.formatarBytes(looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeLeitura());
    }

    public void setVelocidadeLeitura(String velocidadeLeitura) {
        this.velocidadeLeitura = velocidadeLeitura;
    }

    public String getEspacoDisponivel() {
        return espacoDisponivel = Conversor.formatarBytes(looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel());
    }

    public void setEspacoDisponivel(String espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
    }

    public String getEspacoTotal() {
        return Conversor.formatarBytes(looca.getGrupoDeDiscos().getTamanhoTotal());
    }

    public void setEspacoTotal(String espacoTotal) {
        this.espacoTotal = espacoTotal;
    }
}
