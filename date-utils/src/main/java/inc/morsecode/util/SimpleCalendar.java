package inc.morsecode.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Calendar.JANUARY;

import static java.util.Calendar.MONDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;

import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.DAY_OF_YEAR;


public class SimpleCalendar {

    private GregorianCalendar calendar;

	public SimpleCalendar() { this.calendar= new GregorianCalendar(); }

	public SimpleCalendar(TimeZone zone) { this.calendar= new GregorianCalendar(zone); }

	public SimpleCalendar(int year) { this(year, JANUARY, 1); }

	public SimpleCalendar(int year, int month) { this(year, month, 1); }
	
	public SimpleCalendar(int year, int month, int dayOfMonth) { this(year, month, dayOfMonth, 0, 0, 0); }

	public SimpleCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
		this(year, month, dayOfMonth, hourOfDay, minute, 0);
	}

	public SimpleCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
		this.calendar= new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
	}
	
	public SimpleCalendar(Date date) {
		this();
		this.calendar.setTime(date);
	}
	
	
	// simple helpers
	public int year() { return calendar.get(YEAR); }
	public int month() { return calendar.get(MONTH) + 1; }
	public int day() { return calendar.get(DAY_OF_MONTH); }
	public int hour() { return calendar.get(HOUR_OF_DAY); }
	public int minute() { return calendar.get(MINUTE); }
	public int second() { return calendar.get(SECOND); }
	public int millisecond() { return calendar.get(MILLISECOND); }
	public int dayOfYear() { return calendar.get(DAY_OF_YEAR); }
	public int dayOfWeek() { return calendar.get(DAY_OF_WEEK); }
	
	
	public boolean isWeekend() {
		return isSaturday() || isSunday();
	}
	
	public boolean isWeekday() {
		return !isSaturday() && !isSunday();
	}
	
	public boolean isSunday() { return dayOfWeek() == SUNDAY; }
	public boolean isMonday() { return dayOfWeek() == MONDAY; }
	public boolean isTuesday() { return dayOfWeek() == TUESDAY; }
	public boolean isWednesday() { return dayOfWeek() == WEDNESDAY; }
	public boolean isThursday() { return dayOfWeek() == THURSDAY; }
	public boolean isFriday() { return dayOfWeek() == FRIDAY; }
	public boolean isSaturday() { return dayOfWeek() == SATURDAY; }
	
	public boolean isFirstDayOfMonth() { return day() == 1; }
	public boolean isLastDayOfMonth() { return day() == getLastDayInMonth(); }

	public int getLastDayInMonth() { return calendar.getMaximum(DAY_OF_MONTH); }
	
	public void advanceHour(int n) { calendar.add(HOUR_OF_DAY, n); }
	public void advanceMinute(int n) { calendar.add(MINUTE, n); }
	public void advanceSecond(int n) { calendar.add(SECOND, n); }
	public void advanceDay(int n) { calendar.add(DAY_OF_MONTH, n); }
	public void advanceMonth(int n) { calendar.add(MONTH, n); }
	public void advanceYear(int n) { calendar.add(YEAR, n); }
	
	public void setDay(int dayOfMonth) { 
		if (dayOfMonth <= 0) { dayOfMonth= 1; }
		if (dayOfMonth > getLastDayInMonth()) { dayOfMonth= getLastDayInMonth(); }
		calendar.set(DAY_OF_MONTH, dayOfMonth);
	}
	
	public void setMonth(int month) { 
		if (month <= 0) { month= 1; }
		if (month > 12) { month= 12; }
		calendar.set(MONTH, month - 1);
	}
	
	public void setYear(int year) { 
		if (year <= 0) { year= 1; }
		if (year > 10000) { year= 10000; }
		calendar.set(YEAR, year);
	}
	
	public boolean isToday() {
		SimpleCalendar today= new SimpleCalendar();
		
		if (today.year() == year()) {
			if (today.month() == month()) {
				if (today.day() == day()) { 
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isYesterday() {
		SimpleCalendar yesterday= new SimpleCalendar();
		yesterday.advanceDay(-1);
		
		if (yesterday.year() == year()) {
			if (yesterday.month() == month()) {
				if (yesterday.day() == day()) { 
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public boolean isTomorrow() {
		SimpleCalendar tomorrow= new SimpleCalendar();
		tomorrow.advanceDay(1);
		
		if (tomorrow.year() == year()) {
			if (tomorrow.month() == month()) {
				if (tomorrow.day() == day()) { 
					return true;
				}
			}
		}
		
		return false;
	}
	
	public SimpleCalendar zeroTime() {
		calendar.set(HOUR_OF_DAY, 0);
		calendar.set(MINUTE, 0);
		calendar.set(SECOND, 0);
		calendar.set(MILLISECOND, 0);
		return this;
	}
	
	public SimpleCalendar getPreviousMonth() {
		SimpleCalendar month= new SimpleCalendar(calendar.get(YEAR), calendar.get(MONTH), 1);
		month.zeroTime();
		month.advanceMonth(-1);
		return month;
	}
	
	public int getYear() {
		return calendar.get(YEAR);
	}
	
	public int getMonth() {
		return calendar.get(MONTH) + 1;
	}
	
	public SimpleCalendar getNextMonth() {
		SimpleCalendar month= new SimpleCalendar(calendar.get(YEAR), calendar.get(MONTH), 1);
		month.zeroTime();
		month.advanceMonth(1);
		return month;
	}
	
	public String toString() {
		final String STD_FMT="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter= new SimpleDateFormat(STD_FMT);
        formatter.setTimeZone(calendar.getTimeZone());
        return formatter.format(calendar.getTime());
	}
	
	public SimpleCalendar getFirstDateOfMonth() {
		SimpleCalendar date= new SimpleCalendar(getYear(), getMonth(), 1);
		return date;
	}
	public SimpleCalendar getLastDateOfMonth() {
		SimpleCalendar date= new SimpleCalendar(getYear(), getMonth(), getLastDayInMonth());
		return date;
	}


}
