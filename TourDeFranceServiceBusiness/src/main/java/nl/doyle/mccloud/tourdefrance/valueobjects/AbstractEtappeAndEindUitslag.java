package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Iterator;
import java.util.Set;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;

public abstract class AbstractEtappeAndEindUitslag {

	private int etappenummer;
	private String omschrijving;
	//TODO Change this into a single Uitslag object per category. The uitslag object contains the number of recorded positions, etc.
	private Set<GeleTruiUitslag> geleTruiUitslag;
	private Set<GroeneTruiUitslag> groeneTruiUitslag;
	private Set<BolletjesTruiUitslag> bolletjesTruiUitslag;
	/**
	 * The most combative racer
	 */
	private Renner mostCombativeRacer;

	/**
	 * Accepts a {@link ValueObjectVisitor}.
	 * <p>
	 * See the Visitor pattern.
	 * 
	 * @param visitor
	 *            the accepted visitor.
	 */
	public abstract void accept(ValueObjectVisitor visitor);

	public int getPositieInGeleTruiUitslag(final Renner renner) {
		return getPositieInUitslag(geleTruiUitslag, renner);
	}

	public int getPositieInGroeneTruiUitslag(final Renner renner) {
		return getPositieInUitslag(groeneTruiUitslag, renner);
	}

	public int getPositieInBolletjesTruiUitslag(final Renner renner) {
		return getPositieInUitslag(bolletjesTruiUitslag, renner);
	}

	public int getPositionInMostCombativeRacerResult(final Renner renner) {
		int position = 0;
		if (renner.equals(mostCombativeRacer)) {
			position = 1;
		}
		return position;
	}

	protected int getPositieInUitslag(final Set<? extends Uitslag> uitslagen, final Renner renner) {
		int positie = 0;
		boolean found = false;
		Iterator<? extends Uitslag> iterate = uitslagen.iterator();
		while (iterate.hasNext() && !found) {
			Uitslag nextUitslag = iterate.next();
			// TODO Dit moet eigenlijk met de equals method van Renner. Moeten we nog implementeren
			if (nextUitslag.getRenner().equals(renner)) {
				positie = nextUitslag.getPositie();
				found = true;
				// TODO Geoptimaliseerd door deze entry uit deze Uitslagen Set te halen. De andere renners kunnen namelijk nooit op deze
				// positie staan. Wel goed testen of dit werkt.
				uitslagen.remove(nextUitslag);
			}
		}
		return positie;
	}

	/**
	 * @return the bolletjesTruiUitslag
	 */
	public Set<BolletjesTruiUitslag> getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}

	/**
	 * @param bolletjesTruiUitslag
	 *            the bolletjesTruiUitslag to set
	 */
	public void setBolletjesTruiUitslag(final Set<BolletjesTruiUitslag> bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}

	/**
	 * @return the etappenummer
	 */
	public int getEtappenummer() {
		return etappenummer;
	}

	/**
	 * @param etappenummer
	 *            the etappenummer to set
	 */
	public void setEtappenummer(final int etappenummer) {
		this.etappenummer = etappenummer;
	}

	/**
	 * @return the geleTruiUitslag
	 */
	public Set<GeleTruiUitslag> getGeleTruiUitslag() {
		return geleTruiUitslag;
	}

	/**
	 * @param geleTruiUitslag
	 *            the geleTruiUitslag to set
	 */
	public void setGeleTruiUitslag(final Set<GeleTruiUitslag> geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}

	/**
	 * @return the groeneTruiUitslag
	 */
	public Set<GroeneTruiUitslag> getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}

	/**
	 * @param groeneTruiUitslag
	 *            the groeneTruiUitslag to set
	 */
	public void setGroeneTruiUitslag(final Set<GroeneTruiUitslag> groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}

	public Renner getMostCombativeRacer() {
		return mostCombativeRacer;
	}

	public void setMostCombativeRacer(final Renner mostCombativeRacer) {
		this.mostCombativeRacer = mostCombativeRacer;
	}

	/**
	 * @return the omschrijving
	 */
	public String getOmschrijving() {
		return omschrijving;
	}

	/**
	 * @param omschrijving
	 *            the omschrijving to set
	 */
	public void setOmschrijving(final String omschrijving) {
		this.omschrijving = omschrijving;
	}

}
