package Console;

import com.github.britooo.looca.api.core.Looca;

public class Console {
    private String user;
    private String password;
    private Boolean entrou = false;

    // pilares erança e endomorfismo
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEntrou() {
        return entrou;
    }

    public void setEntrou(Boolean entrou) {
        this.entrou = entrou;
    }
}
