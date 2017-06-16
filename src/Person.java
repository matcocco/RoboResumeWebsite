
public class Person {
	private String name;
	private String email;
	private int PersonId;
	
	public int getPersonId(){
		return PersonId;
	}
	public void setPersonId(int p){
		this.PersonId = p;
	}
	public String toString (){
		return name + "\n" + email + "\n";
	}
	public String getName(){
		return name;
	}
	public void setName (String c){
		this.name = c;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail (String cemail){
		this.email = cemail;
	}
	//Throw and exception handle to address number input
}
