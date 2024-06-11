package RAM;

import Banco.BancoConexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RamBanco {

    public void cadastrarDados(RAM metodoRam) throws ClassNotFoundException{

        String sql = "INSERT INTO ram (memoriaDisponivel, memoriaTotal, memoriaUtilizada) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = BancoConexao.getbancoConexao().prepareStatement(sql);
            ps.setString(1, metodoRam.getDisponivel());
            ps.setString(2, metodoRam.getMemoriaTotal());
            ps.setString(3, metodoRam.getMemoriaUtilizada());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
