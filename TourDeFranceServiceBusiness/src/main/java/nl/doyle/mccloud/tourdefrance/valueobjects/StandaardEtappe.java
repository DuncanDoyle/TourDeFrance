package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;

public class StandaardEtappe extends Etappe {
	
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
	 * Set of uitslagen.
	 */
	private Set<EtappeUitslag> etappeUitslag;
	
	
	public int getPositieInEtappeUitslag(final Renner renner) {
		return getPositieInUitslag(etappeUitslag, renner);
	}
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 */
	public StandaardEtappe() {
		super();
	}

	
	

	public Set<EtappeUitslag> getEtappeUitslag() {
		return etappeUitslag;
	}


	public void setEtappeUitslag(final Set<EtappeUitslag> etappeUitslag) {
		this.etappeUitslag = etappeUitslag;
	}
	
	
	

}
