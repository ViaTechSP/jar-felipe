package Log;

import Disco.Disco;
import Disco.DiscoColeta;
import Processador.ProcessadorColeta;
import Processador.Processadores;
import RAM.RAM;
import RAM.RamColeta;
import Processos.ProcessoBanco;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class log {

    private String sistemaOperacional;
    private Integer arquitetura;
    private String hostname;
    private String data;
    private String idMaquina;
    private String mensagem;
    private String logLevel;
    private Integer statusCode;

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public log() {

    }
    //
    public log(String sistemaOperacional, Integer arquitetura, String hostname, String data, String mensagem, String logLevel, Integer statusCode) {
        this.sistemaOperacional = sistemaOperacional;
        this.arquitetura = arquitetura;
        this.hostname = hostname;
        this.data = data;
        this.mensagem = mensagem;
        this.logLevel = logLevel;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return """
                {
                Sistema Operacional: %s
                Arquitetura: %d
                hostname: %s
                Data: %s
                Mensagem: %s
                logLevel: %s
                statusCode: %d
                }
                """.formatted(sistemaOperacional, arquitetura, hostname, data, mensagem, logLevel, statusCode);
    }
}
