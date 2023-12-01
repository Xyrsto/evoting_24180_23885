package evoting_distributed_24180_23885.Cliente;

import java.util.HashMap;

public class Eleitor {
    private String numCC;

    public Eleitor(String numCC) {
        this.numCC = numCC;
    }

    public String getNumCC() {
        return numCC;
    }

    public void setNumCC(String numCC) {
        this.numCC = numCC;
    }

    @Override
    public String toString() {
        return "Eleitor{" + "numCC=" + numCC + '}';
    }

    public boolean hasVoted(HashMap<String,String> votos){
        return votos.containsKey(this.numCC);
    }
}