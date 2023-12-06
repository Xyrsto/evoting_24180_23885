//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2022   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package evoting_distributed_24180_23885.Cliente.Login.BlockchainUtils;

import java.io.Serializable;
import utils.Serializer;

/**
 *
 * @author manso
 */
public class Vote implements Serializable {

    private String eleitor;
    private String candidato;
    

    public Vote(String eleitor, String candidato) {
        this.eleitor = eleitor;
        this.candidato = candidato;
    }

    public String getEleitor() {
        return eleitor;
    }

    public void setEleitor(String eleitor) {
        this.eleitor = eleitor;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    @Override
    public String toString() {
         return eleitor + "->" + candidato;         
    }

    public String toText() {
        return Serializer.objectToBase64(this);
    }

    public static Vote fromText(String obj) {
        return (Vote)Serializer.base64ToObject(obj);
    }

    @Override
    public int hashCode() {
        return toText().hashCode();
    }

    @Override
    public boolean equals(Object t) {
        if (t instanceof Vote) {
            return this.toText().equals(((Vote) t).toText());
        }
        return false;
    }
        //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202312050910L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2023  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////

}
