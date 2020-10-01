package auto.kolcsonzo;

public class Ugyfelek { 
	
	private int id;
	private String igazolvanySzam;
	private String vezetekNev;
	private String keresztNev;
	private String telefonSzam;
	private String email;
	
	



	public Ugyfelek(int id, String igazolvanySzam, String vezetekNev, String keresztNev, String telefonSzam,
			String email) {
		super();
		this.id = id;
		this.igazolvanySzam = igazolvanySzam;
		this.vezetekNev = vezetekNev;
		this.keresztNev = keresztNev;
		this.telefonSzam = telefonSzam;
		this.email = email;
	}






	public Ugyfelek(String igazolvanySzam, String vezetekNev, String keresztNev, String telefonSzam, String email) {
		super();
		this.igazolvanySzam = igazolvanySzam;
		this.vezetekNev = vezetekNev;
		this.keresztNev = keresztNev;
		this.telefonSzam = telefonSzam;
		this.email = email;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getIgazolvanySzam() {
		return igazolvanySzam;
	}






	public void setIgazolvanySzam(String igazolvanySzam) {
		this.igazolvanySzam = igazolvanySzam;
	}






	public String getVezetekNev() {
		return vezetekNev;
	}






	public void setVezetekNev(String vezetekNev) {
		this.vezetekNev = vezetekNev;
	}






	public String getKeresztNev() {
		return keresztNev;
	}






	public void setKeresztNev(String keresztNev) {
		this.keresztNev = keresztNev;
	}






	public String getTelefonSzam() {
		return telefonSzam;
	}






	public void setTelefonSzam(String telefonSzam) {
		this.telefonSzam = telefonSzam;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	@Override
	public String toString() {
		return "Ugyfelek [id=" + id + ", igazolvanySzam=" + igazolvanySzam + ", vezetekNev=" + vezetekNev
				+ ", keresztNev=" + keresztNev + ", telefonSzam=" + telefonSzam + ", email=" + email + "]";
	}
	
	 
	
	
}
