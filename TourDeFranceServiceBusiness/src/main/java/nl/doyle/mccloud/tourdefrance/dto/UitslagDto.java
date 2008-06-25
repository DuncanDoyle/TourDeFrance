package nl.doyle.mccloud.tourdefrance.dto;


public class UitslagDto {
	
	private int positie;
	private String positieBedrag;
	private RennerDto renner;
	private DeelnemerDto deelnemer;
	
	public UitslagDto() {
	}

	public int getPositie() {
		return positie;
	}

	public void setPositie(int positie) {
		this.positie = positie;
	}

	public RennerDto getRenner() {
		return renner;
	}

	public void setRenner(RennerDto renner) {
		this.renner = renner;
	}

	public DeelnemerDto getDeelnemer() {
		return deelnemer;
	}

	public void setDeelnemer(DeelnemerDto deelnemer) {
		this.deelnemer = deelnemer;
	}

	public String getPositieBedrag() {
		return positieBedrag;
	}

	public void setPositieBedrag(String positieBedrag) {
		this.positieBedrag = positieBedrag;
	}

	
}
