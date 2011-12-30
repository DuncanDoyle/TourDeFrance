package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;

@Entity
@Table(name="EINDUITSLAG")
public class EindUitslag extends AbstractEtappeAndEindUitslag {
	
	@ManyToOne
	@JoinColumn(name="EERSTEUITVALLER", unique=true)
	private Renner eersteUitvaller;
	
	@ManyToOne
	@JoinColumn(name="RODELANTAREN", unique=true)
	private Renner rodeLantaren;

	/**
	 * The most combative racer
	 */
	@ManyToOne
	@JoinColumn(name="MOST_COMBATIVE_RACER")
	private Renner mostCombativeRacer;

	
	public EindUitslag() {
	}

	
	/**
	 * Accepts a {@link ValueObjectVisitor} and calls its {@link ValueObjectVisitor#visit(EindUitslag)} method.
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
	
	public int getPositieInRodeLantarenUitslag(final Renner renner) {
		int positie = 0;
		if (renner.equals(rodeLantaren)) {
			positie = 1;
		}
		return positie;
	}
	
	
	
	public int getPositieInEersteUitvallerUitslag(final Renner renner) {
		int positie = 0;
		if (renner.equals(eersteUitvaller)) {
			positie = 1;
		}
		return positie;
	}
	
	public int getPositionInMostCombativeRacerResult(final Renner renner) {
		int position = 0;
		if (renner.equals(mostCombativeRacer)) {
			position = 1;
		}
		return position;
	}
	
	
	/**
	 * @return the eersteUitvaller
	 */
	public Renner getEersteUitvaller() {
		return eersteUitvaller;
	}

	/**
	 * @param eersteUitvaller the eersteUitvaller to set
	 */
	public void setEersteUitvaller(final Renner eersteUitvaller) {
		this.eersteUitvaller = eersteUitvaller;
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
	public void setRodeLantaren(final Renner rodeLantaren) {
		this.rodeLantaren = rodeLantaren;
	}
	
	public Renner getMostCombativeRacer() {
		return mostCombativeRacer;
	}

	public void setMostCombativeRacer(final Renner mostCombativeRacer) {
		this.mostCombativeRacer = mostCombativeRacer;
	}
	
}
