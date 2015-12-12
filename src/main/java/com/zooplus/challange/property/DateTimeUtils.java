package com.zooplus.challange.property;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {
	public static final String DATEFORMAT = "dd/MM/yyyy";
	private static DateTimeUtils instance = null;
	public static final String DEFAULT_TIMEZONE = "Europe/Istanbul";
	private TimeZone localTZ = null;

	/**
	 * DateTimeUtils have to be inited first.
	 * 
	 * @return
	 */
	public synchronized static DateTimeUtils getInstance() {
		if (instance == null) {
			instance = new DateTimeUtils();
			instance.init();
		}
		return instance;
	}

	public void init() {
		localTZ = TimeZone.getTimeZone(DEFAULT_TIMEZONE);
	}

	public Date getUTCNowDateTime() {
		Date now = new Date();
		now.setTime(now.getTime() - this.localTZ.getOffset(now.getTime()));
		return now;
	}

	public Date getDate(String dateStr) {
		DateFormat format = new SimpleDateFormat(DATEFORMAT, Locale.ENGLISH);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Date getDateFromTime(Date date) {
		DateFormat df = new SimpleDateFormat(DATEFORMAT);

		try {
			return df.parse(df.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param durationInDays
	 * @return
	 */
	public Date addDayToDate(int durationInDays) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, durationInDays);
		Date d2 = rightNow.getTime();
		return d2;
	}

	public Date addDifference(Date date, int differenceType, int difference) {
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTime(date);
		startDateCal.add(differenceType, difference);
		return startDateCal.getTime();
	}
}
