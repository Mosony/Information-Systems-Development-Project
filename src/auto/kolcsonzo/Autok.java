package auto.kolcsonzo;

public class Autok { 
	
	private int id;
	private String marka;
	private String rendSzam;
	private String tipus;
	private String statusz;
	private String kolcsonzesiDij;
	
	



	public Autok(int id, String marka, String rendSzam, String tipus, String statusz,
			String kolcsonzesiDij) {
		super();
		this.id = id;
		this.marka = marka;
		this.rendSzam = rendSzam;
		this.tipus = tipus;
		this.statusz = statusz;
		this.kolcsonzesiDij = kolcsonzesiDij;
	}






	public Autok(String marka, String rendSzam, String tipus, String statusz, String kolcsonzesiDij) {
		super();
		this.marka = marka;
		this.rendSzam = rendSzam;
		this.tipus = tipus;
		this.statusz = statusz;
		this.kolcsonzesiDij = kolcsonzesiDij;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getmarka() {
		return marka;
	}






	public void setmarka(String marka) {
		this.marka = marka;
	}






	public String getrendSzam() {
		return rendSzam;
	}






	public void setrendSzam(String rendSzam) {
		this.rendSzam = rendSzam;
	}






	public String gettipus() {
		return tipus;
	}






	public void settipus(String tipus) {
		this.tipus = tipus;
	}






	public String getstatusz() {
		return statusz;
	}






	public void setstatusz(String statusz) {
		this.statusz = statusz;
	}






	public String getkolcsonzesiDij() {
		return kolcsonzesiDij;
	}






	public void setkolcsonzesiDij(String kolcsonzesiDij) {
		this.kolcsonzesiDij = kolcsonzesiDij;
	}






	@Override
	public String toString() {
		return "Ugyfelek [id=" + id + ", marka=" + marka + ", rendSzam=" + rendSzam
				+ ", tipus=" + tipus + ", statusz=" + statusz + ", kolcsonzesiDij=" + kolcsonzesiDij + "]";
	}
	
	 
	
	
}
