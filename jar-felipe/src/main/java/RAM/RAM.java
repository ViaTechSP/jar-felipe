package RAM;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;

public class RAM {

    Looca looca = new Looca();
    private String disponivel;
    private String memoriaTotal;
    private String memoriaUtilizada;

    public String getDisponivel() {
        return   disponivel = Conversor.formatarBytes(looca.getMemoria().getDisponivel());
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getMemoriaTotal() {
        return memoriaTotal = Conversor.formatarBytes(looca.getMemoria().getTotal());
    }

    public void setMemoriaTotal(String memoriaTotal) {
        this.memoriaTotal = memoriaTotal;
    }

    public String getMemoriaUtilizada() {
        return memoriaUtilizada = Conversor.formatarBytes(looca.getMemoria().getEmUso());
    }

    public void setMemoriaUtilizada(String memoriaUtilizada) {
        this.memoriaUtilizada = memoriaUtilizada;
    }
}
