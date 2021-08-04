package ru.stoupin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = ConfigurableBeanFactory.)
public class MySpringBean {
	private static final Log logger = LogFactory.getLog(MySpringBean.class);
	
	private String value;
	
	public MySpringBean() {
		value = "empty value";
		logger.info("MySpringBean created!!!!!!!!!");
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
