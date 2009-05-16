package nl.doyle.mccloud.tourdefrance.dto;


public final class UitslagDto {
	
	private int positie;
	private String positieBedrag;
	private RennerDto renner;
	private DeelnemerDto deelnemer;
	
	public UitslagDto() {
	}

	public int getPositie() {
		return positie;
	}

	public void setPositie(final int positie) {
		this.positie = positie;
	}

	public RennerDto getRenner() {
		return renner;
	}

	public void setRenner(final RennerDto renner) {
		this.renner = renner;
	}

	public DeelnemerDto getDeelnemer() {
		return deelnemer;
	}

	public void setDeelnemer(final DeelnemerDto deelnemer) {
		this.deelnemer = deelnemer;
	}

	public String getPositieBedrag() {
		return positieBedrag;
	}

	public void setPositieBedrag(final String positieBedrag) {
		this.positieBedrag = positieBedrag;
	}

	
}
