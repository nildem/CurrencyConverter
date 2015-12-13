package com.zooplus.challange;

import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.zooplus.challange.hibernate.DataManager;
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
		InputStream stream = this.getServletContext().getResourceAsStream(configFile);
		if (stream == null)
			System.out.println(configFile + " cannot be read to load properties");
		PropertiesUtils.init(new PropertyFileUtils(stream));
	}
}
