package com.zooplus.challange;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.zooplus.challange.hibernate.DataManager;
import com.zooplus.challange.property.DateTimeUtils;
import com.zooplus.challange.property.PropertiesUtils;
import com.zooplus.challange.property.PropertyFileUtils;

public class ProjectLoader extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3282625696151199070L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			init(config.getInitParameter("configFile"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataManager.getInstance().createAdmin();
		
	}

	void init(String configFile) throws Exception {
		PropertiesUtils.init(new PropertyFileUtils(new File(this.getServletContext().getRealPath(configFile))));
	}
}
