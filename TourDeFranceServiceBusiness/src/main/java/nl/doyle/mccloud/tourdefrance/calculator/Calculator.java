package nl.doyle.mccloud.tourdefrance.calculator;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dto.DeelnemerBedragDto;

public interface Calculator {

	public abstract DeelnemerBedragDto getDeelnemerAndBedragAfterEtappe(int deelnemernummer, int etappenummer);
	public abstract List<DeelnemerBedragDto> getAllDeelnemersAndBedragAfterEtappe(int etappenummer);
	public abstract List<DeelnemerBedragDto> getAllDeelnemersAndBedragAtEnd();

	
}
