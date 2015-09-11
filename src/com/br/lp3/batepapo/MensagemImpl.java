 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.batepapo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author 31427782
 */
public class MensagemImpl extends UnicastRemoteObject implements Mensagem {
    
    private List<String> usuarios;
    
    public MensagemImpl() throws RemoteException
    {
        usuarios = new ArrayList<>();
    }
    
    public boolean verificaNome(String nome)
    {
        boolean resposta = false;
        for (String usuario : usuarios) {
            if(usuario.equalsIgnoreCase(nome)){
                resposta = true;
                break;
            }
        }
        return resposta;
    }
    
    
    @Override
    public List<String> getUsuarios() throws RemoteException {
        return usuarios;
    }    
    

    @Override
    public void conecta(String nome) throws RemoteException {
        if(!verificaNome(nome)){
            usuarios.add(nome);
            System.out.println(nome+" entrou na sala!");
        }
    }

    @Override
    public void diz(String remetente, String msg) throws RemoteException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String datastr = formatter.format(cal.getTime());
        
        System.out.println(" ("+datastr+") "+remetente+" diz: "+msg);
    }

    @Override
    public void diz(String remetente, String destinatario, String msg) throws RemoteException {
       if(verificaNome(destinatario)){
           Calendar cal = Calendar.getInstance();
           SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
           String datastr = formatter.format(cal.getTime());
           System.out.println(remetente+" diz para: "+destinatario+":"+msg);
       }
        
    }
    
    

    @Override
    public void desconecta(String nome) throws RemoteException {
        usuarios.remove(nome);
        System.out.println(nome+" saiu da sala!");
    }
    
}
