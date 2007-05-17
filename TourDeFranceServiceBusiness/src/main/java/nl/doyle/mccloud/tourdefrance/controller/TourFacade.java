package nl.doyle.mccloud.tourdefrance.controller;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dto.DeelnemerRennerDto;
import nl.doyle.mccloud.tourdefrance.exceptions.DataNotFoundException;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;

public interface TourFacade {

	public List<DeelnemerRennerDto> getAllDeelnemersAndRenners();
	public Etappe getEtappeWithUitslag(int etappeNummer) throws DataNotFoundException;
}
