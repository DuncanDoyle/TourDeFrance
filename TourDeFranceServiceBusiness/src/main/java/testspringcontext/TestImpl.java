package testspringcontext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class TestImpl implements Test {
	 
	private String message;

	private static Log logger = LogFactory.getLog(TestImpl.class);
	
	public TestImpl() {
	}
	
	public void sayHello() {
		logger.error(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
