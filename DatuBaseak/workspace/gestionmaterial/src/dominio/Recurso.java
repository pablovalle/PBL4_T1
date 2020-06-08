package dominio;

public class Recurso
{
  protected int id;
  protected String nombre;
  protected String descripci�n;
  protected String ubicaci�n;
  protected int idResponsable;
  
  public Recurso(int id, String nombre, String descripci�n,String ubicaci�n, int idResponsable)
  {
    this.ubicaci�n=ubicaci�n;
    this.nombre=nombre;
    this.descripci�n=descripci�n;
    this.id=id;
    this.idResponsable = idResponsable;
  }
  public Recurso (Recurso r){
	  this.id = r.id;
	  this.nombre = r.nombre;
	  this.descripci�n = r.descripci�n;
	  this.ubicaci�n = r.ubicaci�n;
	  this.idResponsable = r.idResponsable;
  }
  public String getNombre(){return nombre;}
  public String getDescripci�n(){return descripci�n;}
  public int getId(){return id;}
  public String getUbicaci�n(){return ubicaci�n;}
  public int getIdResponsable(){return idResponsable;}
}