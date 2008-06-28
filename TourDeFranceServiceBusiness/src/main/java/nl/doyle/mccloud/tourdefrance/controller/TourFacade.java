package nl.doyle.mccloud.tourdefrance.controller;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dto.AbstractEtappeAndEindUitslagDto;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerWithRennersDto;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;

public interface TourFacade {

	public List<DeelnemerWithRennersDto> getAllDeelnemersAndRenners();

	public AbstractEtappeAndEindUitslagDto getEtappeDtoWithUitslag(int etappeNummer);

	public AbstractEtappeAndEindUitslag getEtappeWithUitslag(int etappeNummer);
	
	public abstract AbstractEtappeAndEindUitslag getEtappe(int etappeNummer);
	
	public Etappe getEtappeWithStartAndFinish(int etappeNummer);

	public void saveEtappe(AbstractEtappeAndEindUitslag etappe);

	public int getHighestEtappeNummer();
}
