package model;


public class Vendedor {
    
    // Atributos
    public String nombre;
    public String apellidos;
    public Integer matricula;
    private Double ventas ;
    
    public Vendedor() {
            this.ventas = 0.0;
    }

    public Vendedor(String nombre, String apellidos, int matricula) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.ventas = 0.0;
    }

    public Vendedor(String nombre, String apellidos, int matricula,Double ventas) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.ventas = ventas;
    }

	public Vendedor(String registro) {

		String[] campos = registro.split(",");
		this.matricula = Integer.parseInt(campos[0]);					
		this.nombre = campos[1];						
		this.apellidos = campos[2];
		this.ventas = Double.parseDouble(campos[3]);
	}

    public String getNombre() 					{  return nombre;    }
    public void setNombre(String nombre) 		{  this.nombre = nombre;}
    public String getApellidos() 				{  return apellidos;    }
    public void setApellidos(String apellidos) 	{  this.apellidos = apellidos;    }
    public int getMatricula() 					{  return matricula;    }
    public void setMatricula(int matricula) 	{  this.matricula = matricula;    }
    public double getVentas() 					{  return ventas;    }
    public void setVentas(double ventas) 		{  this.ventas = ventas;    }
  

// Métdos de negocio

 
}
