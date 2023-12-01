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
package evoting_distributed_24180_23885.ServidorMiner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created on 28/09/2022, 11:13:39
 *
 * @author IPT - computer
 * @version 1.0
 */
public class MinerP2P {

    //ticket para com números para testar
    AtomicInteger ticket;
    //noce que mina a mensagem
    AtomicInteger nonce;
    //listener do mineiro
    MiningListener listener;
    //array de thread para minar em paralelo 
    MinerTHR worker;

    public MinerP2P(MiningListener listener) {
        this.ticket = new AtomicInteger(0);
        this.nonce = new AtomicInteger(0);
        this.worker = null;
        this.listener = listener;
    }

    public void startMining(String data, int dificulty) {
        Random rnd = new Random();
        //parar o mineiro se ainda estiver a minar
        stopMining(999);
        //criar novos objetos partilhados
        //começar num numero aleatório
        ticket = new AtomicInteger(Math.abs(rnd.nextInt() + 1));
        nonce = new AtomicInteger(0);
        //Criar o array de threas e polas a correr
        worker = new MinerTHR(ticket, nonce, dificulty, data, listener);
        worker.start();
    }

    public void stopMining(int number) {
        if (worker != null) {
            worker.stop(number);
        }
    }

    public int getTicket() {
        return ticket.get();
    }

    public boolean isMining() {
        return worker != null &&  nonce.get() == 0;
    }

    /**
     * *
     * Espera que as threads executem terminem o trabalho e devolve o nonce
     *
     * @return
     * @throws Exception
     */
    public int getNonce() throws Exception {
        if (worker != null || worker.isAlive()) {
            worker.join();
            return nonce.get();
        } else {
            throw new Exception("Not mining");
        }
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::      THREAD         :::::::::::::::::::::::::::::::::    
    ///////////////////////////////////////////////////////////////////////////
    private class MinerTHR extends Thread {

        AtomicInteger ticket;
        AtomicInteger nonce;
        int dificulty;
        String data;
        MiningListener listener;

        public MinerTHR(AtomicInteger ticket, AtomicInteger nonce, int dificulty, String data, MiningListener listener) {
            this.ticket = ticket;
            this.nonce = nonce;
            this.dificulty = dificulty;
            this.data = data;
            this.listener = listener;
        }

        public void stop(int number) {
            nonce.set(number);
            interrupt();
        }

        public void run() {
            //:::::::::::::  L I S T E N E R  ::::::::::::::::::::::::::::::
            listener.onStartMining(Thread.currentThread().getName(), dificulty);
            //String of zeros
            String zeros = String.format("%0" + dificulty + "d", 0);
            int number;
            while (nonce.get()== 0) {
                //calculate hash of block   
                number = ticket.getAndIncrement();
                //:::::::::::::  L I S T E N E R  ::::::::::::::::::::::::::::::
                listener.onMining(number);
                String hash = getHash(number + data);
                //Nounce found
                if (hash.startsWith(zeros)) {
                    //:::::::::::::  L I S T E N E R  ::::::::::::::::::::::::::::::
                    listener.onNounceFound(number);
                    nonce.set(number);
                }
            }
            //:::::::::::::  L I S T E N E R  ::::::::::::::::::::::::::::::
            listener.onStopMining(nonce.get());
        }
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::      I N T E G R I T Y         :::::::::::::::::::::::::::::::::    
    ///////////////////////////////////////////////////////////////////////////
    public static String getHash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MinerP2P.class.getName()).log(Level.SEVERE, null, ex);
            return data;
        }
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202209281113L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2022  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
