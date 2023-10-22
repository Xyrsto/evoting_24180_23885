/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author rodri
 */
public class JavaApplication6 {

    /**
     * @param args the command line arguments
     */
    private static class TakanoParalel extends Thread{
        double mult;
        double divis;
        double result;

        public TakanoParalel(double mult, double divis) {
            this.mult = mult;
            this.divis = divis;
        }
        
        @Override
        public void run(){
            this.result =  mult * Math.atan(1/divis);
        }
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        double result = 0;
        TakanoParalel tp1 = new TakanoParalel(12.0, 49.0);
        TakanoParalel tp2 = new TakanoParalel(32.0, 57.0);
        TakanoParalel tp3 = new TakanoParalel(-5.0, 239.0);
        TakanoParalel tp4 = new TakanoParalel(12.0, 110443.0);
        
        tp1.start();
        tp2.start();
        tp3.start();
        tp4.start();
        
        tp1.join();
        tp2.join();
        tp3.join();
        tp4.join();
        
        result = 4*(tp1.result + tp2.result + tp3.result + tp4.result);
        System.out.println(result);
    }
    
}
