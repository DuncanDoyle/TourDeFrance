package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class EditEtappeUitslagFormController extends SimpleFormController {

	private static final Log logger = LogFactory.getLog(EditEtappeUitslagFormController.class);

	private RennerDao rennerDao;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object)
	 */
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		// TODO Auto-generated method stub
		return super.onSubmit(command);
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		//Maak een nieuw FormBackingObject aan
		EtappeUitslagCommand etappeUitslag = new EtappeUitslagCommand();
		
		return etappeUitslag;
	}



	/**
	 * @return the rennerDao
	 */
	public RennerDao getRennerDao() {
		return rennerDao;
	}





	/**
	 * @param rennerDao the rennerDao to set
	 */
	public void setRennerDao(RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	} 


	
	
	
	
	
	
	
	
	
}
