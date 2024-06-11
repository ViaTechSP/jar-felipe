package Processador;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.SQLException;

public class ProcessadorColeta {

    public static void coletaDeProcessador() throws ClassNotFoundException {
        Looca looca = new Looca();
        Sistema sistema = new Sistema();


        String modeloProcessador = looca.getProcessador().getNome();
        String frequecia = String.valueOf(looca.getProcessador().getFrequencia());
        String tempoAtividade = Conversor.formatarSegundosDecorridos(sistema.getTempoDeAtividade());


        Processadores processador = new Processadores();

        processador.setNomeCPU(modeloProcessador);
        processador.setFrequencia(frequecia);
        processador.setTempoAtividade(tempoAtividade);
        new ProcessadorBanco().cadastrarDados(processador);
    }

}
