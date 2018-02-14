/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteto_psp;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;

/**
 *
 * @author asus410
 */
public class ClienteTO_PSP {

    //direccion donde esta el server
    public static final String HOST = "localhost";
    //puerto por el que nos vamos a conectar
    public static int puerto = 3500;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //creo una instancia de BufferedReader para almacenar los datos
        //pasados por el cliente
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Socket cliente;
        
        try{
            
            //instanciamos un Socket con la direccion y el puerto para la conexion
            cliente = new Socket(HOST,puerto);
            
            //instancia de un objeto DAtaOutputSream para enviar datos al servidor
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            
            //instancia de un objeto DataInputStream para recivir datos del servidor
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            //leemos del servidor
            String mensaje = entrada.readUTF();
            System.out.println(mensaje);
            //con el bucle le pasamos opcion al servidor leemos lo que nos devuelve
            //comprovamos si es time o end
            //sino lo mostramos por pantalla y volvemos a introducir comando
            //mientras sea distinto de exit
            do{              
                String mensajeSalida =in.readLine();
                salida.writeUTF(mensajeSalida);
                mensaje = entrada.readUTF();
                if(mensaje.equalsIgnoreCase("end")){
                    System.out.println("Te has desconectado del servidor");
                }else if(mensaje.startsWith("time")){
                   String aux =  mensaje.substring(4);
                    Long tiempo = Long.parseLong(aux);
                    Long tiempo2 = System.currentTimeMillis();
                    System.out.println(""+(tiempo2-tiempo)+ " Milisegundos");
                }else{
                    System.out.println(mensaje);
                }      
            }while(!mensaje.equalsIgnoreCase("end"));
            //cerramos el Socket
            cliente.close();
            System.exit(0);
            
            in.close();entrada.close();salida.close();
            
        }catch (Exception e) {
            System.out.println("Servidor desconectado");
        }
    }
    
}
