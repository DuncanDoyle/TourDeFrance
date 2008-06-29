package nl.doyle.mccloud.tourdefrance.valueobjects;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;

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
	public void accept(ValueObjectVisitor visitor) {
		visitor.visit(this);
	}

}
