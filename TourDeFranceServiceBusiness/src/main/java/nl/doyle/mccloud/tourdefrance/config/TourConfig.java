package nl.doyle.mccloud.tourdefrance.config;

public interface TourConfig {
	
	public abstract int getAantalEtappeUitslagen();
	public abstract int getAantalEtappeGeleTruiUitslagen();
	public abstract int getAantalEtappeGroeneTruiUitslagen();
	public abstract int getAantalEtappeBolletjesTruiUitslagen();
	public abstract int getAantalEtappeWitteTruiUitslagen();
	public abstract int getAantalEtappeRodeLantaren();
	public abstract int getAantalEinduitslagGeleTruiUitslagen();
	public abstract int getAantalEinduitslagGroeneTruiUitslagen();
	public abstract int getAantalEinduitslagBolletjesTruiUitslagen();
	public abstract int getAantalEinduitslagWitteTruiUitslagen();
	public abstract int getAantalEinduitslagRodeLantaren();
	public abstract int getAantalEinduitslagEersteUitvaller();
	public abstract int getNumberOfStageMostCombative();
	public abstract int getNumberOfFinalMostCombative();
	public abstract int getNumberOfStagePositionHundred();
}
