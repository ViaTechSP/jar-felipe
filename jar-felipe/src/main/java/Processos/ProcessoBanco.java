package Processos;

import Banco.BancoConexao;
import com.github.britooo.looca.api.group.processos.Processo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessoBanco {
    public void cadastrarDados(Processo processo) throws ClassNotFoundException {

        String sql = "INSERT INTO processos (pid, usoCpu, usoMemoria, bytesUtilizados, memoriaVirtualUtilizada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = BancoConexao.getbancoConexao().prepareStatement(sql)) {
            ps.setInt(1, processo.getPid());
            ps.setDouble(2, processo.getUsoCpu());
            ps.setDouble(3, processo.getUsoMemoria());
            ps.setLong(4, processo.getBytesUtilizados());
            ps.setLong(5, processo.getMemoriaVirtualUtilizada());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}