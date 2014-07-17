package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;

/**
 * The stage class. Contains the stage result objects, white jersey and red lantern.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Entity
@Table(name="STANDAARDETAPPE")
public class StandaardEtappe extends Etappe {
	
	/**
	 * Set of uitslagen.
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="STANDAARDETAPPE_ETAPPEUITSLAG", 
			joinColumns = 
				@JoinColumn(name="STANDAARDETAPPE_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
			inverseJoinColumns = {
				@JoinColumn(name="ETAPPEUITSLAG_ETAPPENUMMER", referencedColumnName="ETAPPENUMMER"),
				@JoinColumn(name="ETAPPEUITLSLAG_POSITIE", referencedColumnName="POSITIE")
		}
	)
	private Set<EtappeUitslag> etappeUitslag;
	
	@ManyToOne
	@JoinColumn(name="RODELANTAREN")
	private Renner rodeLantaren;
		
	/**
	 * The most combative racer
	 */
	@ManyToOne
	@JoinColumn(name="MOST_COMBATIVE_RACER")
	private Renner mostCombativeRacer;
	
	/**
	 * The most combative racer
	 */
	@ManyToOne
	@JoinColumn(name="POSITION_HUNDRED_RACER")
	private Renner positionHundredRacer;

	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 */
	public StandaardEtappe() {
		super();
	}

	
	/**
	 * Accepts a {@link ValueObjectVisitor} and calls its {@link ValueObjectVisitor#visit(StandaardEtappe)} method.
	 * <p>
	 * See the Visitor pattern.
	 * 
	 * @param visitor
	 *            the accepted visitor.
	 */
	@Override
	public void accept(final ValueObjectVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * @param renner the cyclist for which to determine its position in the result
	 * @return the cyclist's position in this stage's result 
	 */
	public int getPositieInEtappeUitslag(final Renner renner) {
		return getPositieInUitslag(etappeUitslag, renner);
	}	
	
	public int getPositionInMostCombativeRacerResult(final Renner renner) {
		int position = 0;
		if (renner.equals(mostCombativeRacer)) {
			position = 1;
		}
		return position;
	}
	
	public int getPositionInPositionHundredResult(final Renner renner) {
		int position = 0;
		if (renner.equals(positionHundredRacer)) {
			position = 1;
		}
		return position;
	}
	
	public int getPositionRodeLantarenUitslag(final Renner renner) {
		int position = 0;
		if (renner.equals(rodeLantaren)) {
			position = 1;
		}
		return position;
	}

	public Set<EtappeUitslag> getEtappeUitslag() {
		return etappeUitslag;
	}


	public void setEtappeUitslag(final Set<EtappeUitslag> etappeUitslag) {
		this.etappeUitslag = etappeUitslag;
	}


	/**
	 * @return the rodeLantaren
	 */
	public Renner getRodeLantaren() {
		return rodeLantaren;
	}

	/**
	 * @param rodeLantaren the rodeLantaren to set
	 */
	public void setRodeLantaren(Renner rodeLantaren) {
		this.rodeLantaren = rodeLantaren;
	}
	
	public Renner getMostCombativeRacer() {
		return mostCombativeRacer;
	}

	public void setMostCombativeRacer(final Renner mostCombativeRacer) {
		this.mostCombativeRacer = mostCombativeRacer;
	}
	
	public Renner getPositionHundredRacer() {
		return positionHundredRacer;
	}

	public void setPositionHundredRacer(final Renner positionHundredRacer) {
		this.positionHundredRacer = positionHundredRacer;
	}

}
