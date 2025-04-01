package model;

public class User {

	private int Id;
	private String name;
	private int age;
	private String city;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	public User(int id, String name, int age, String city) {
		super();
		Id = id;
		this.name = name;
		this.age = age;
		this.city = city;
	}


	public int getId() {		return Id;	}
	public void setId(int id) {		Id = id;	}
	public String getName() {		return name;	}
	public void setName(String name) {		this.name = name;	}
	public int getAge() {		return age;	}
	public void setAge(int age) {		this.age = age;	}
	public String getCity() {		return city;	}
	public void setCity(String city) {		this.city = city;	}

	
	
}
