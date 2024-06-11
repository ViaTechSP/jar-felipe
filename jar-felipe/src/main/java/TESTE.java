import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;

public class TESTE {
    public static void main(String[] args) {

        Looca looca = new Looca();
        Sistema sistema = new Sistema();
        System.out.println(looca.getRede().getGrupoDeInterfaces().getInterfaces());



    }
}
