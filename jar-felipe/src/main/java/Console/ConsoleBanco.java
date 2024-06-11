package Console;

import Banco.BancoConexao;
import com.github.britooo.looca.api.core.Looca;
import Log.log;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleBanco {

    public void selectDeDados(Console console) throws ClassNotFoundException {

        String sql = "SELECT * FROM empresa WHERE nomeFantasia = ? and senha = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Looca looca = new Looca();
        log log = new log();

        try {
            ps = BancoConexao.getbancoConexao().prepareStatement(sql);
            ps.setString(1, console.getUser());
            ps.setString(2, console.getPassword());

            rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Erro ao realizar login");
                console.setEntrou(false);

                // configurando log para erro de login
                log.setSistemaOperacional(looca.getSistema().getSistemaOperacional());
                log.setArquitetura(looca.getSistema().getArquitetura());
                log.setHostname(looca.getRede().getParametros().getHostName());
                log.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date()));
                log.setMensagem("Erro ao realizar login: usuário ou senha incorretos");
                log.setLogLevel("ERROR");
                log.setStatusCode(401);

                // exibindo e salvando log
                System.out.println(log.toString());
                salvarLog(log);

            } else {
                System.out.println("Login realizado");
                console.setEntrou(true);

                // configurando log para sucesso de login
                log.setSistemaOperacional(looca.getSistema().getSistemaOperacional());
                log.setArquitetura(looca.getSistema().getArquitetura());
                log.setHostname(looca.getRede().getParametros().getHostName());
                log.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date()));
                log.setMensagem("Login realizado com sucesso");
                log.setLogLevel("INFO");
                log.setStatusCode(200);

                // exibindo e salvando log
                System.out.println(log.toString());
                salvarLog(log);
            }

        } catch (SQLException e) {
            // configurando log para erro de SQL
            log.setSistemaOperacional(looca.getSistema().getSistemaOperacional());
            log.setArquitetura(looca.getSistema().getArquitetura());
            log.setHostname(looca.getRede().getParametros().getHostName());
            log.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date()));
            log.setMensagem("Erro ao realizar consulta SQL: " + e.getMessage());
            log.setLogLevel("ERROR");
            log.setStatusCode(500);

            // exibindo e salvando log
            System.out.println(log.toString());
            salvarLog(log);

            throw new RuntimeException("Erro ao realizar consulta SQL", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                // configurando log para erro ao fechar recursos
                log.setSistemaOperacional(looca.getSistema().getSistemaOperacional());
                log.setArquitetura(looca.getSistema().getArquitetura());
                log.setHostname(looca.getRede().getParametros().getHostName());
                log.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date()));
                log.setMensagem("Erro ao fechar recursos: " + e.getMessage());
                log.setLogLevel("ERROR");
                log.setStatusCode(500);

                // exibindo e salvando log
                System.out.println(log.toString());
                salvarLog(log);

                throw new RuntimeException("Erro ao fechar recursos", e);
            }
        }
    }

    private void salvarLog(log log) {
        // formatar a data atual para usar no nome do arquivo de log
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String nomeArquivoLog = String.format("./logs/log-%s.txt", dataAtual);

        try (FileWriter writer = new FileWriter(nomeArquivoLog, true)) {
            writer.write(log.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}
