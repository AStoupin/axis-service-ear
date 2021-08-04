package ru.stoupin;

import java.util.Optional;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.jws.WebService;

import org.apache.axis.MessageContext;
import org.apache.axis.session.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebService
//@Singleton
//@Stateful
public class MyService extends SpringBeanAutowiringSupport{
	private static final Log logger = LogFactory.getLog(MyService.class);
	
	@Autowired
	MySpringBean mySpringBean;
	
	public String trigger() {
		logger.info("call triggered");
		
		getValue();
		
		Session session = MessageContext.getCurrentContext().getSession();
		
		
		if(mySpringBean==null || session == null) {
			logger.info("empty!!!!!!!!!!");
			return "empty";
		}
		

		mySpringBean.setValue("triggered");
		session.set("mySpringBean", mySpringBean.getValue());
		
		return mySpringBean.getValue();
	}

	public String unTrigger() {
		logger.info("call unTriggered");
		getValue();
		
		mySpringBean.setValue("unTriggered");
		Session session = MessageContext.getCurrentContext().getSession();
		session.set("mySpringBean", mySpringBean.getValue());

		return mySpringBean.getValue();
	}
	
	public String getValue() {
		
		Session session = MessageContext.getCurrentContext().getSession();
		String result = (String) Optional.ofNullable(session.get("mySpringBean")).orElse("empty"); 
		logger.info("Current session value: " + result);
		
		
		
		
		return result;
	}

}
