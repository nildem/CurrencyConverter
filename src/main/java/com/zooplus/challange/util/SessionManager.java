package com.zooplus.challange.util;

import java.util.List;
import java.util.Vector;

public class SessionManager {

	private static SessionManager instance;
	private List<String> sessions;

	public static synchronized SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();

		}
		return instance;
	}

	public void addSession(String id) {
		if (sessions == null)
			sessions = new Vector<String>();
		sessions.add(id);
	}

	public void removeSession(String id) {
		if (sessions != null)
			sessions.remove(id);
	}

	public boolean isValidSession(String id) {
		return !(sessions == null) && sessions.contains(id);

	}

}
