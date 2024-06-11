package Disco;

import Banco.BancoConexao;
import RAM.RamColeta;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscoBanco {

    public void cadastrarDados(Disco Disco) {

        String sql = "INSERT INTO disco (velocidadeLeitura, espacoTotal, espacoDisponivel) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = BancoConexao.getbancoConexao().prepareStatement(sql);
            ps.setString(1, Disco.getVelocidadeLeitura());
            ps.setString(2, Disco.getEspacoTotal());
            ps.setString(3, Disco.getEspacoDisponivel());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
