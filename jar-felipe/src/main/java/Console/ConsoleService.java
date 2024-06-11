package Console;

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

public class ConsoleService {

    public static void main(String[] args) throws ClassNotFoundException {
        Looca looca = new Looca();
        RAM ram = new RAM();
        Disco disco = new Disco();
        Processadores processadores = new Processadores();
        ProcessoGrupo processoGrupo = looca.getGrupoDeProcessos();

        String usuario;
        String senha;
        Scanner in = new Scanner(System.in);

        System.out.println("""
                  ___                ___ 
                 (o o)              (o o)
                (  V  ) Bem Vindo! (  V  )
                --m-m----------------m-m--
                           
                Vamos Observar as informações  atuais do seu PC! 
                                    
                ███████╗███████╗██╗     ██╗██████╗ ███████╗    ███╗   ███╗   ███████╗
                ██╔════╝██╔════╝██║     ██║██╔══██╗██╔════╝    ████╗ ████║   ██╔════╝
                █████╗  █████╗  ██║     ██║██████╔╝█████╗      ██╔████╔██║   ███████╗
                ██╔══╝  ██╔══╝  ██║     ██║██╔═══╝ ██╔══╝      ██║╚██╔╝██║   ╚════██║
                ██║     ███████╗███████╗██║██║     ███████╗    ██║ ╚═╝ ██║██╗███████║
                ╚═╝     ╚══════╝╚══════╝╚═╝╚═╝     ╚══════╝    ╚═╝     ╚═╝╚═╝╚══════╝
                  """);

        System.out.println("Nome da Sua Empresa:");
        usuario = in.nextLine();

        System.out.println("Senha:");
        senha = in.nextLine();

        Console console = new Console();
        console.setUser(usuario);
        console.setPassword(senha);
        new ConsoleBanco().selectDeDados(console);

        if (!console.getEntrou()) {
            System.out.println("LOGIN INVÁLIDO! TENTE NOVAMENTE.");
        } else {

            Sistema sistema = looca.getSistema();
            String sistemaOperacional = sistema.getSistemaOperacional();
            String fabricante = sistema.getFabricante();
            String arquitetura = String.valueOf(sistema.getArquitetura());

            boolean executar = true;
            while (executar) {
                System.out.println("""
                        O que deseja?
                        1-Coletar Dados 
                        2-Sair
                        """);

                switch (in.nextInt()) {
                    case 1 -> {
                        System.out.println("""
                                O que deseja?
                                1-Iniciar Coleta
                                2-Sair
                                """);

                        Scanner escolhaScanner = new Scanner(System.in);
                        Timer timer = new Timer();
                        boolean executar02 = true;

                        while (executar02) {
                            int escolha = escolhaScanner.nextInt();

                            if (escolha == 1) {
                                System.out.println("""
                                        2-Parar
                                        Digite a Opção:
                                        """);

                                TimerTask tarefa = new TimerTask() {
                                    Integer contagem = 0;

                                    public void run() {
                                        contagem++;

                                        // coletando processos
                                        List<Processo> processosOrdenados = processoGrupo.getProcessos()
                                                .stream()
                                                .sorted(Comparator.comparingDouble(Processo::getUsoCpu).reversed())
                                                .limit(5) // limitando aos 5 primeiros processos com maior uso de CPU
                                                .toList();

                                        ProcessoBanco processoBanco = new ProcessoBanco();

                                        for (Processo proc : processosOrdenados) {
                                            try {
                                                processoBanco.cadastrarDados(proc);
                                            } catch (ClassNotFoundException e) {
                                                System.out.println("Erro ao cadastrar dados do processo: " + e.getMessage());
                                            }
                                        }

                                        // exibiçao e log dos dados coletados
                                        StringBuilder processosInfo = new StringBuilder();
                                        for (Processo proc : processosOrdenados) {
                                            processosInfo.append(String.format("""
                                                    PID: %d
                                                    Uso CPU: %.1f
                                                    Uso memória: %.1f
                                                    Bytes utilizados: %d
                                                    Memória virtual utilizada: %d
                                                    """,
                                                    proc.getPid(), proc.getUsoCpu(), proc.getUsoMemoria(),
                                                    proc.getBytesUtilizados(), proc.getMemoriaVirtualUtilizada()));
                                        }

                                        String coletaDados = String.format("""
                                                 ----------------------------------------
                                                 Coletando Dados.. %d
                                                 
                                                 Memória RAM info:                       
                                                 Memória RAM em uso: %s                 
                                                 Memória RAM Total: %s                   
                                                 Memória RAM Disponível: %s               
                                                                                        
                                                 Disco Rígido info:                     
                                                 Velocidade de Leitura: %s              
                                                 Espaço Disponível: %s                   
                                                 Espaço Total: %s                       
                                                                                        
                                                 Processador info:                      
                                                 Frequência: %sGHz                     
                                                 Tempo de Atividade: %s 
                                                 
                                                 Processos com maior uso de CPU:
                                                 %s
                                                """, contagem, ram.getMemoriaUtilizada(), ram.getMemoriaTotal(),
                                                ram.getDisponivel(), disco.getVelocidadeLeitura(), disco.getEspacoDisponivel(),
                                                disco.getEspacoTotal(), processadores.getFrequencia(), processadores.getTempoAtividade(), processosInfo);

                                        // Imprimindo os dados coletados no console
                                        System.out.println(coletaDados);

                                        // Coleta de dados de disco, processador e RAM
                                        DiscoColeta.coletarDadosDisco();
                                        try {
                                            ProcessadorColeta.coletaDeProcessador();
                                        } catch (ClassNotFoundException e) {
                                            System.out.println("Erro ao coletar dados do processador: " + e.getMessage());
                                        }

                                        try {
                                            RamColeta.coletaDeRam();
                                        } catch (ClassNotFoundException e) {
                                            System.out.println("Erro ao coletar dados da RAM: " + e.getMessage());
                                        }
                                    }
                                };

                                timer.scheduleAtFixedRate(tarefa, 200, 5000L);
                            } else if (escolha == 2) {
                                timer.cancel();
                                executar02 = false;
                                System.out.println("Coleta de Dados Cancelada!");
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("Obrigado!");
                        executar = false;
                    }
                }
            }
        }
    }
}