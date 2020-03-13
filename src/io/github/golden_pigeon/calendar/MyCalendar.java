package io.github.golden_pigeon.calendar;
/**
 * 原创日历类
 * 用于执行各种日历操作
 * 依赖于Date类
 * @author Golden_Pigeon
 *
 */
public class MyCalendar {
	private Date date;
	public MyCalendar() {
		this.date = new Date();
	}
	/**
	 * 打印指定年份的日历
	 * @param year 指定的年份
	 */
	public void printCalendarByYear(int year) throws IllegalDateException{
		for(int month = 1; month <= 12; month++)
			printCalendarByMonth(year, month);
	}
	/**
	 * 打印指定的年份及月份的日历
	 * @param year 指定的年份
	 * @param month 指定的月份
	 */
	public void printCalendarByMonth(int year, int month) throws IllegalDateException{
		date.setTotal(year, month, 1);
		int[] calendar = new int[42];
		int pos = date.getCurrentDayInWeek();
		if(pos == 7)
			pos = 0;
		int maxDays = date.maxDaysInCurrentMonth();
		for(int i = 0; i < maxDays; i++) {
			calendar[pos + i] = i + 1;
		}
		System.out.println(month + "月");
		System.out.println("-------------------------------");
		System.out.println("周日\t周一\t周二\t周三\t周四\t周五\t周六");
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				if(calendar[i * 7 + j] != 0)
					System.out.print(calendar[i * 7 + j] + "\t");
				else
					System.out.print("\t");
			}
			System.out.print("\n");
		}
		System.out.println("-------------------------------\n");
	}
	
	public String getDayInWeek(int year, int month, int day) throws IllegalDateException{
		date.setTotal(year, month, day);
		int dayInWeek = date.getCurrentDayInWeek();
		String weekDay = "周";
		switch(dayInWeek) {
		case 1:
			weekDay += "一";
			break;
		case 2:
			weekDay += "二";
			break;
		case 3:
			weekDay += "三";
			break;
		case 4:
			weekDay += "四";
			break;
		case 5:
			weekDay += "五";
			break;
		case 6:
			weekDay += "六";
			break;
		default:
			weekDay += "日";
		}
		return weekDay;
	}
}
