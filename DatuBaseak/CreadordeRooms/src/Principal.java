import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Principal {
	
	Scanner teclado;
	
	public Principal() {
		teclado= new Scanner(System.in);
		Random random = new Random();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("habitaciones");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    
		System.out.println("Introduzca el numero de pisos: ");
		int pisos= teclado.nextInt();
		teclado.nextLine();
		System.out.println("Introduzca el id del hotel: ");
		int id= teclado.nextInt();
		teclado.nextLine();
		System.out.println("Introduzca el precio maximo de luxury: ");
		int maxLux= teclado.nextInt();
		teclado.nextLine();
		System.out.println("Introduzca el precio minimo de luxury: ");
		int minLux= teclado.nextInt();
		teclado.nextLine();
		System.out.println("Introduzca el precio maximo de estandar: ");
		int maxest= teclado.nextInt();
		teclado.nextLine();
		System.out.println("Introduzca el precio minimo de estandar: ");
		int minest= teclado.nextInt();
		teclado.nextLine();
		int seed=(int) (Math.random()*2);
		String tipo;
		int aforo;
		int precio;
		for(int i=0; i<pisos; i++) {
			for(int j=0; j<12; j++) {
				seed=(int) (Math.random()*4);
				if(seed!=5) {
					tipo="estandar";
					aforo=(int)(Math.random()*4)+1;
					precio=random.nextInt(maxest)+minest;
				}
				else {
					tipo="luxury";
					seed=(int) (Math.random()*2);
					if(seed==1) {
						aforo=4;
					}
					else {
						aforo=3;
					}
					precio=random.nextInt(maxLux)+minLux;
				}
			    printWriter.printf("INSERT INTO habitacion VALUE("+((i+1)*100+j+1)+","+id+","+aforo+",'exterior','vacio',"+precio+",'"+tipo+"');\n");
			}
		}
		printWriter.close();
		
		
		
	}

	public static void main(String[] args) {
		Principal programa= new Principal();

	}

}
