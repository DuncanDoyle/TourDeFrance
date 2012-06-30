package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.Entity;
import javax.persistence.Table;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;

@Entity
@Table(name="PLOEGENTIJDRIT")
public class PloegenTijdrit extends Etappe {

	/**
	 * Accepts a {@link ValueObjectVisitor} and calls its {@link ValueObjectVisitor#visit(PloegenTijdrit)} method.
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

}
