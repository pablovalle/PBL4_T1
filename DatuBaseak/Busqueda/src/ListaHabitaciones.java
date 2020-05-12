import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class ListaHabitaciones extends AbstractListModel<Habitacion> {

	List<Habitacion> listaHabitaciones;
	public void modelo() {
		listaHabitaciones=new ArrayList<Habitacion>();
	}
	@Override
	public Habitacion getElementAt(int index) {
		// TODO Auto-generated method stub
		return listaHabitaciones.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return listaHabitaciones.size();
	}

}
