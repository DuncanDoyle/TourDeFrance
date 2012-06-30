package nl.doyle.mccloud.tourdefrance.valueobjects.visitor;

import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

public interface ValueObjectVisitor {
	
	/**
	 * Visits a {@link StandaardEtappe} value object.
	 * 
	 * @param stage
	 *            the {@link StandaardEtappe} to visit
	 */
	public abstract void visit(StandaardEtappe stage);
	
	/**
	 * Visits a {@link PloegenTijdrit} value object.
	 * 
	 * @param stage
	 *            the {@link PloegenTijdrit} to visit
	 */
	public abstract void visit(PloegenTijdrit stage);
	
	/**
	 * Visits a {@link EindUitslag} value object.
	 * 
	 * @param endResult
	 *            the {@link EindUitslag} to visit
	 */
	public abstract void visit(EindUitslag endResult);

}
