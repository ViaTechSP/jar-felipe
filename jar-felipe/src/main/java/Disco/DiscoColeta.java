package Disco;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;

import java.util.List;

public class DiscoColeta {

    public static void coletarDadosDisco() {
        Looca looca = new Looca();
        Sistema sistema = new Sistema();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();

        List<Volume> discos = grupoDeDiscos.getVolumes();



        for (Volume disco : discos) {

            String velocidadeLeitura = Conversor.formatarBytes(grupoDeDiscos.getDiscos().get(0).getBytesDeLeitura());
            String totalMemoria = Conversor.formatarBytes(disco.getTotal());
            String discoDisponivel = Conversor.formatarBytes (disco.getDisponivel());

            Disco metodoDisc = new Disco();
            metodoDisc.setVelocidadeLeitura(velocidadeLeitura);
            metodoDisc.setEspacoTotal(totalMemoria);
            metodoDisc.setEspacoDisponivel(discoDisponivel);


            new DiscoBanco().cadastrarDados(metodoDisc);

        }



    }
}
