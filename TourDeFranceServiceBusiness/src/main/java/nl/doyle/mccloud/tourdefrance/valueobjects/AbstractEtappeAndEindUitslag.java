package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="TOUR.ETAPPEANDEINDUITSLAG")
public abstract class AbstractEtappeAndEindUitslag {

	@Id
	@Column(name="ETAPPENUMMER")
	private int etappenummer;
	
	@Column(name="OMSCHRIJVING")
	private String omschrijving;
	//TODO Change this into a single Uitslag object per category. The uitslag object contains the number of recorded positions, etc.

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ETAPPE_GELETRUIUITSLAG", 
			joinColumns = 
				@JoinColumn(name="ETAPPE_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
			inverseJoinColumns = {
				@JoinColumn(name="GELETRUI_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
				@JoinColumn(name="GELETRUI_POSITIE", referencedColumnName="POSITIE")
		}
	)
	private Set<GeleTruiUitslag> geleTruiUitslag;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ETAPPE_GROENETRUIUITSLAG", 
			joinColumns = 
				@JoinColumn(name="ETAPPE_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
			inverseJoinColumns = {
				@JoinColumn(name="GROENE_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
				@JoinColumn(name="GROENETRUI_POSITIE", referencedColumnName="POSITIE")
		}
	)
	private Set<GroeneTruiUitslag> groeneTruiUitslag;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ETAPPE_BOLLETJESRUIUITSLAG", 
			joinColumns = 
				@JoinColumn(name="ETAPPE_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
			inverseJoinColumns = {
				@JoinColumn(name="BOLLETJES_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
				@JoinColumn(name="BOLLETJES_POSITIE", referencedColumnName="POSITIE")
		}
	)
	private Set<BolletjesTruiUitslag> bolletjesTruiUitslag;

	/**
	 * De witte trui.
	 */
	@ManyToOne
	@JoinColumn(name="WITTETRUI", unique=true)
	private Renner witteTrui;
	
	
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

	public int getPositieInWitteTruiUitslag(final Renner renner) {
		int positie = 0;
		if (renner.equals(witteTrui)) {
			positie = 1;
		}
		return positie;
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

	/**
	 * @return the witteTrui
	 */
	public Renner getWitteTrui() {
		return witteTrui;
	}

	/**
	 * @param witteTrui the witteTrui to set
	 */
	public void setWitteTrui(final Renner witteTrui) {
		this.witteTrui = witteTrui;
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
