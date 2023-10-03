/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885.SecurityUtils;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.bouncycastle.jce.provider.BouncyCastleProvider;



/**
 *
 * @author rodri
 */
public class Providers {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        Provider providers[] = Security.getProviders();
        //todos os fornecedores do segurança
        for (Provider provider : providers) {
            System.out.println("\n::::::::::::::::::::::::::::::::::::");
            System.out.println("Provider " + provider.getName());
            System.out.println("::::::::::::::::::::::::::::::::::::");
            //serviços fornecidos
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                System.out.printf("%-20s %s\n", service.getType(), service.getAlgorithm());
            }
        }
    }

    public static List<Provider.Service> getServices(String serviceType) {
        ArrayList<Provider.Service> list = new ArrayList<>();
        //lista do fornecedores
        Provider providers[] = Security.getProviders();
        for (Provider provider : providers) {
            //Serviços fornecidos
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                //Serviço de Integridade
                if (service.getType().equalsIgnoreCase(serviceType)) {
                    list.add(service);
                }
            }
        }        
        return list;
    }

    public static List<String> getStringServices(String serviceType) {
        ArrayList<String> list = new ArrayList<>();
        //lista do fornecedores
        Provider providers[] = Security.getProviders();
        for (Provider provider : providers) {
            //Serviços fornecidos
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                //Serviço de Integridade
                if (service.getType().equalsIgnoreCase(serviceType)) {
                    list.add(service.getProvider().getName() + ":" + service.getAlgorithm());
                }
            }
        }
        Collections.sort(list);
        return list;
    }
}
