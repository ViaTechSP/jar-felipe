package Processador;

import Banco.BancoConexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessadorBanco {

    public void cadastrarDados(Processadores processador) throws ClassNotFoundException {

        String sql = "INSERT INTO processador (frequencia, tempoAtividade) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = BancoConexao.getbancoConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(processador.getFrequencia()));
            ps.setString(2, processador.getTempoAtividade());
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
