package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Set;

public class DeelnemerWithRennersDto extends DeelnemerDto {

	private Set<RennerDto> renners;

	public DeelnemerWithRennersDto() {
	}

	/**
	 * @return the renners
	 */
	public Set<RennerDto> getRenners() {
		return renners;
	}

	/**
	 * @param renners
	 *            the renners to set
	 */
	public void setRenners(Set<RennerDto> renners) {
		this.renners = renners;
	}

}
