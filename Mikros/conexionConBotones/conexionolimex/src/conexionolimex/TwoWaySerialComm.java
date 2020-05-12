package conexionolimex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class TwoWaySerialComm implements ActionListener
{
	Thread lectura, escritura;
	SerialReader serialLector;
	SerialWriter serialEnvio;
	
	final String mensaje_ok = "OK$";
	final String mensaje_error = "EZ$";
	
    public TwoWaySerialComm()
    {
        //super();
		conectar();
		
    }
    
	public void conectar() {
		try {
			this.connect("COM8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                
		        serialLector = new SerialReader(in);
		        serialEnvio = new SerialWriter(out);

		        lectura = new Thread(serialLector);
		        escritura = new Thread(serialEnvio);
		        
		        lectura.start();
		        escritura.start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    /** */
    public static class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                    System.out.print(new String(buffer,0,len));
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }

    /** */
	  public static class SerialWriter implements Runnable {

		    OutputStream out;
		    boolean running;
		    
		    public SerialWriter( OutputStream out ) {
		      this.out = out;
		    }

		    public void enviarSeñal(byte [] signal) {
		    	try {
					this.out.write(signal);
					//if (signal == 66) running = false;
					
				} catch (IOException e) {
					System.out.println("error de envio");
				}
		    }
		    
		    public void run() {
		    	running = true;

		    	System.out.println("Escritura running");
		        while( running ) {
		
		        }
		        System.out.println("Fin escritura");

		    }

		    
		  }
	  
	  /*
    public static void main ( String[] args )
    {
    	System.out.println("aupa");
        try
        {
            (new TwoWaySerialComm()).connect("COM8");
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		switch(comando) {
		case "ok":
			serialEnvio.enviarSeñal(mensaje_ok.getBytes());
			break;
		case "no":
			serialEnvio.enviarSeñal(mensaje_error.getBytes());
			break;		
		
		
		}
		
	}
}