package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.List;

import javax.servlet.ServletException;

import nl.doyle.mccloud.tourdefrance.config.TourConfig;
import nl.doyle.mccloud.tourdefrance.dao.UitslagBedragDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;
import nl.doyle.mccloud.tourdefrance.web.spring.command.UitslagBedragCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/editUitslagBedrag.htm")
@SessionAttributes("uitslagBedragCommand")
public class EditUitslagBedragFormController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log logger = LogFactory.getLog(EditUitslagBedragFormController.class);

	private static final String SUCCESS_VIEW = "index.htm";

	/**
	 * Tour configuration.
	 */
	private TourConfig config;

	/**
	 * The {@link UitslagBedrag} retrieved from the DB.
	 */
	private List<UitslagBedrag> dbUitslagBedragen;

	/**
	 * The DAO for {@link UitslagBedrag}.
	 */
	private UitslagBedragDao uitslagBedragDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView onSubmit(@ModelAttribute("uitslagBedragCommand") final UitslagBedragCommand uitslagBedragCommand)
			throws ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving uitslagBedragen!!");
		}
		if (logger.isDebugEnabled()) {
			for (int counter = 0; counter < uitslagBedragCommand.getEtappe().length; counter++) {
				logger.debug("UitslagBedrag Etappe positie " + (counter + 1) + "= " + uitslagBedragCommand.getEtappe()[counter]);

			}
		}

		// Eerst voor de gewone etappe
		saveUitslagBedragen(uitslagBedragCommand.getEtappe(), Categorien.Etappe);
		saveUitslagBedragen(uitslagBedragCommand.getGeleTrui(), Categorien.GeleTrui);
		saveUitslagBedragen(uitslagBedragCommand.getGroeneTrui(), Categorien.GroeneTrui);
		saveUitslagBedragen(uitslagBedragCommand.getBolletjesTrui(), Categorien.BolletjesTrui);
		saveUitslagBedragen(uitslagBedragCommand.getWitteTrui(), Categorien.WitteTrui);
		saveUitslagBedragen(uitslagBedragCommand.getRodeLantaren(), Categorien.RodeLantaren);
		saveUitslagBedragen(uitslagBedragCommand.getGeleTruiEind(), Categorien.GeleTruiEind);
		saveUitslagBedragen(uitslagBedragCommand.getGroeneTruiEind(), Categorien.GroeneTruiEind);
		saveUitslagBedragen(uitslagBedragCommand.getBolletjesTruiEind(), Categorien.BolletjesTruiEind);
		saveUitslagBedragen(uitslagBedragCommand.getWitteTruiEind(), Categorien.WitteTruiEind);
		saveUitslagBedragen(uitslagBedragCommand.getRodeLantarenEind(), Categorien.RodeLantarenEind);
		saveUitslagBedragen(uitslagBedragCommand.getEersteUitvallerEind(), Categorien.EersteUitvallerEind);
		saveUitslagBedragen(uitslagBedragCommand.getMostCombativeStage(), Categorien.MostCombativeStage);
		saveUitslagBedragen(uitslagBedragCommand.getMostCombativeFinal(), Categorien.MostCombativeFinal);
		saveUitslagBedragen(uitslagBedragCommand.getPositionHundredStage(), Categorien.PositionHundredStage);

		return new ModelAndView(new RedirectView(SUCCESS_VIEW));
	}

	private void saveUitslagBedragen(final double[] uitslagBedragen, final Categorien categorie) {

		for (int teller = 0; teller < uitslagBedragen.length; teller++) {
			// Loop nu door alle uitslagbedragen en kijk of de juiste entry gevonden kan worden.
			boolean found = false;
			for (UitslagBedrag nextUitslagBedrag : dbUitslagBedragen) {
				if (nextUitslagBedrag.getCategorie().equals(categorie) && nextUitslagBedrag.getPositie() == (teller + 1)) {
					found = true;
					// Als de waarde nog niet hetzelfde is als de waarde in de database dan slaan we de gegevens op.
					if (nextUitslagBedrag.getBedrag() != uitslagBedragen[teller]) {
						nextUitslagBedrag.setBedrag(uitslagBedragen[teller]);
						uitslagBedragDao.saveUitslagBedrag(nextUitslagBedrag);
					}
				}
			}
			if (found == false) {
				// entry is nog niet aanwezig in de DB. Nieuwe entry aanmaken en opslaan
				UitslagBedrag nieuwUitslagBedrag = new UitslagBedrag();
				nieuwUitslagBedrag.setCategorie(categorie);
				nieuwUitslagBedrag.setPositie(teller + 1);
				nieuwUitslagBedrag.setBedrag(uitslagBedragen[teller]);
				uitslagBedragDao.saveUitslagBedrag(nieuwUitslagBedrag);
			}
		}
	}

	/**
	 * Initialiseer het formbackingobject. In dit form zijn dat de UitslagenEnBedragen
	 * 
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("uitslagBedragCommand")
	protected UitslagBedragCommand formBackingObject() throws ServletException, EditEtappeFormRequestException {
		// Gegevens van mogelijke vorige bezoek in sessie wissen
		dbUitslagBedragen = uitslagBedragDao.loadAllUitslagBedragen();
		UitslagBedragCommand uitslagBedrag = new UitslagBedragCommand(config.getAantalEtappeUitslagen(), config
				.getAantalEtappeGeleTruiUitslagen(), config.getAantalEtappeGroeneTruiUitslagen(), config
				.getAantalEtappeBolletjesTruiUitslagen(), config.getAantalEtappeWitteTruiUitslagen(), config.getAantalEtappeRodeLantaren(), config
				.getAantalEinduitslagGeleTruiUitslagen(), config.getAantalEinduitslagGroeneTruiUitslagen(), config
				.getAantalEinduitslagBolletjesTruiUitslagen(), config.getAantalEinduitslagWitteTruiUitslagen(), config
				.getAantalEinduitslagRodeLantaren(), config.getAantalEinduitslagEersteUitvaller(), config.getNumberOfStageMostCombative(),
				config.getNumberOfFinalMostCombative(), config.getNumberOfStagePositionHundred());
		// loop door alle uitslagen heen en zet de waardes goed
		for (UitslagBedrag nextUitslagBedrag : dbUitslagBedragen) {
			switch (nextUitslagBedrag.getCategorie()) {
			case Etappe:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getEtappe().length) {
					uitslagBedrag.getEtappe()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GeleTrui:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGeleTrui().length) {
					uitslagBedrag.getGeleTrui()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GroeneTrui:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGroeneTrui().length) {
					uitslagBedrag.getGroeneTrui()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case BolletjesTrui:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getBolletjesTrui().length) {
					uitslagBedrag.getBolletjesTrui()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case WitteTrui:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getWitteTrui().length) {
					uitslagBedrag.getWitteTrui()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case RodeLantaren:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getRodeLantaren().length) {
					uitslagBedrag.getRodeLantaren()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GeleTruiEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGeleTruiEind().length) {
					uitslagBedrag.getGeleTruiEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GroeneTruiEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGroeneTruiEind().length) {
					uitslagBedrag.getGroeneTruiEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case BolletjesTruiEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getBolletjesTruiEind().length) {
					uitslagBedrag.getBolletjesTruiEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case WitteTruiEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getWitteTruiEind().length) {
					uitslagBedrag.getWitteTruiEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case RodeLantarenEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getRodeLantarenEind().length) {
					uitslagBedrag.getRodeLantarenEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case EersteUitvallerEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getEersteUitvallerEind().length) {
					uitslagBedrag.getEersteUitvallerEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case MostCombativeStage:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getMostCombativeStage().length) {
					uitslagBedrag.getMostCombativeStage()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case MostCombativeFinal:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getMostCombativeFinal().length) {
					uitslagBedrag.getMostCombativeFinal()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case PositionHundredStage:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getPositionHundredStage().length) {
					uitslagBedrag.getPositionHundredStage()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			}
		}
		return uitslagBedrag;
	}

	/**
	 * @return the uitslagBedragDao
	 */
	public UitslagBedragDao getUitslagBedragDao() {
		return uitslagBedragDao;
	}

	/**
	 * @param uitslagBedragDao
	 *            the uitslagBedragDao to set
	 */
	public void setUitslagBedragDao(final UitslagBedragDao uitslagBedragDao) {
		this.uitslagBedragDao = uitslagBedragDao;
	}

	/**
	 * @return the config
	 */
	public TourConfig getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(final TourConfig config) {
		this.config = config;
	}

}
