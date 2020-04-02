package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class ComunicacionTCP extends Observable implements Runnable {
	private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    
    //Hilo de recepci�n
    @Override
    public void run() {
    	try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Esperando...");
			this.socket = server.accept();
			System.out.println("Aceptado!");
			
			//Reader
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			this.reader = new BufferedReader(isr);
			
			//Writer 
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			this.writer = new BufferedWriter(osw);
			
			while(true) {
				recibirMensaje();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Mandar un mensaje
    public void mandarMensaje(String mensaje){
    	new Thread(
    			()->{
    				try {
						writer.write(mensaje+"\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    	).start();
    }

    //Recibir mensaje
    public void recibirMensaje() throws IOException{
    	String line = reader.readLine();
		System.out.println("<<<"+line);
    	
    	setChanged();
		notifyObservers(line);
		clearChanged();
    	
    }

    public void cerrarConexion(){
    	try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    
    
    
    
    

}
