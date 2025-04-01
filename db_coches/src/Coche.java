
public class Coche {

	
	private String Matricula;
	private String Modelo;
	private String Color;
	private int year;

	
	
	public Coche() {
		
	};
	
	
	public Coche(String matricula, String modelo, String color, int year) {
		super();
		Matricula = matricula;
		Modelo = modelo;
		Color = color;
		this.year = year;

	}
	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
// Sobreescritura del método equals para el objeto
	public boolean equals(String mat) {
		if (this.Matricula== mat) return true;
		else return false;
	}
/*	
	  public boolean equals(Object obj) {
	      if(!(obj instanceof Coche)) {
	         return false;
	      }
	      Coche c = (Coche)obj;
	     return this.Matricula == c.getMatricula();
	   }
*/	  
// Sobreescitura del método toString
	public String toString() {
		String str="";
		// json output
		//str = "{Matricula: "+ this.Matricula + ",Modelo: "+this.Modelo + ",Color: "+this.Color + ",Año: "+this.year+"}";
		// CSV output
		
		str = this.Matricula + ";"+this.Modelo + ";"+this.Color + ";"+this.year; 
		return str;
	}
	
} // Coche
