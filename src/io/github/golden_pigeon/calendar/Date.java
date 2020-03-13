package io.github.golden_pigeon.calendar;
/**
 * 这个类用于表示日期以及处理一些有关日期的基本操作
 * @author Golden_Pigeon
 *
 */
public class Date {
	/**
	 * currentXXX表示当前的日期，初始化为2000年1月1日星期六
	 * tempXXX用于储存用户设定的日期
	 * 每当用户设定日期后，执行dayInWeekStandardize()函数，是“当前日期”前进/后退到设定日期，
	 * 并与此同时计算出用户设定日期的星期。
	 * <类的作者>在plus/minusDay等方法外修改以下字段后要注意在方法结束前确保对应字段相等
	 * 变量设置成private以避免用户私自修改有关变量而没有进行星期的标准化造成错误。
	 * 
	 */
	private int currentYear = 2000;
	private int currentMonth = 1;
	private int currentDay = 1;
	private int tempYear;
	private int tempMonth;
	private int tempDay;
	private int currentDayInWeek = 6;
	private final int[] daysPerMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; 
	/**
	 * 构造方法
	 * @param year 设定年份
	 * @param month 设定月份
	 * @param day 设定日
	 */
	public Date(int year, int month, int day) throws IllegalDateException{
		this.tempYear = year;
		this.tempMonth = month;
		this.tempDay = day;
		dayInWeekStandardize(); //使当前日期前进/后退，并计算星期
		
	}
	/**
	 * 默认构造方法
	 * 初始化为2000年1月1日
	 */
	public Date() {
		this.tempYear = 2000;
		this.tempMonth = 1;
		this.tempDay = 1;
	}
	/**
	 * 判断当前年份是否为闰年
	 * @return 是闰年则返回true,否则返回false.
	 */
	public boolean isLeap() {
		if((currentYear % 4 == 0)&&((currentYear % 100 != 0)||(currentYear % 400 == 0)))
			return true;
		else
			return false;
	}
	/**
	 * 判断当前月份是否为闰月
	 * @return 是闰月则返回true,否则返回false.
	 */
	public boolean isLeapMonth() {
		if(isLeap()&&currentMonth == 2) // 只有闰年的二月才可能是闰月
			return true;
		else
			return false;
	}
	/**
	 * 计算当前月份总天数
	 * @return 当前月份总天数
	 */
	public int maxDaysInCurrentMonth() {
		return daysPerMonth[currentMonth - 1] + (isLeapMonth() ? 1 : 0); //如果是闰月则加一天
	}

	/**
	 * 提供给用户的日期前进方法
	 * @param days 前进的天数
	 */
	public void plusDays(int days) {
		for(int i = 0; i < days; i++)
			plusDay();
		//确保每次操作后tempXXX与currentXXX是一致的
		tempDay = currentDay;
		tempMonth = currentMonth;
		tempYear = currentYear;
	}
	/**
	 * 底层方法，日期前进一天，并按照规则进位，同时更新星期。
	 */
	private void plusDay() {
		if(currentDay < maxDaysInCurrentMonth())
			currentDay++;
		else {
			currentDay = 1;
			plusMonth();
		}
		if(currentDayInWeek < 7)
			currentDayInWeek++;
		else
			currentDayInWeek = 1;
	}
	/**
	 * plusDay的辅助方法，增加月份，并按规则进位
	 */
	private void plusMonth() {
		if(currentMonth < 12)
			currentMonth++;
		else {
			//进位
			currentMonth = 1;
			plusYear();
		}
	}
	/**
	 * plusMonth辅助方法，增加月份
	 */
	private void plusYear() {
		currentYear++;
		if(currentYear == 0)
			currentYear++;
	}
	
	/**
	 * 提供给用户的日期后退方法
	 * @param days 后退的天数
	 */
	public void minusDays(int days) {
		for(int i = 0; i < days; i++)
			minusDay();
		//确保每次操作后tempXXX与currentXXX是一致的
		tempDay = currentDay;
		tempMonth = currentMonth;
		tempYear = currentYear;
	}
	/**
	 * 日期后退一天，其他与plusDay类似，不再赘述。
	 */
	private void minusDay() {
		if(currentDay > 1)
			currentDay--;
		else {
			minusMonth();
			currentDay = maxDaysInCurrentMonth();
		}
		if(currentDayInWeek > 1)
			currentDayInWeek--;
		else
			currentDayInWeek = 7;
	}
	
	private void minusMonth() {
		if(currentMonth > 1)
			currentMonth--;
		else {
			currentMonth = 12;
			minusYear();
		}
	}
	
	private void minusYear() {
		currentYear--;
		if(currentYear == 0)
			currentYear--;
	}
	/**
	 * 使当前日期前进/后退，直至用户设定日期，并计算星期。
	 */
	private void dayInWeekStandardize() throws IllegalDateException {
		if(!isDateLegal())
			throw new IllegalDateException();
		int cmp = tempCmpCurrent(); //用于cmp判断设定的日期在当前日期之前还是之后以决定前进/后退
		if(cmp == 0) // 若当前日期与设定日期相同，则不执行任何操作
			return;
		else if(cmp > 0) // 日期前进，直到设定日期，下同
			while(tempCmpCurrent() > 0)
				plusDay();
		else
			while(tempCmpCurrent() < 0)
				minusDay();
	}
	/**
	 * 比较设定的日期在当前日期之前还是之后
	 * @return 在之前返回负值，在之后返回正值，否则返回0.
	 */
	private int tempCmpCurrent() {
		if(tempYear > currentYear)
			return 1;
		else if(tempYear == currentYear)
			if(tempMonth > currentMonth)
				return 1;
			else if(tempMonth == currentMonth)
				if(tempDay > currentDay)
					return 1;
				else if(tempDay == currentDay)
					return 0;
				else
					return -1;
			else
				return -1;
		else
			return -1;
	}
	
	private boolean isDateLegal() {
		if(tempYear == 0 || tempMonth < 1 || tempDay < 1 || tempMonth > 12)
			return false;
		else {
			boolean leap = (tempYear % 4 == 0)&&((tempYear % 100 != 0)||(tempYear % 400 == 0))&&(tempMonth == 2);
			if(tempDay > (daysPerMonth[tempMonth - 1] + (leap ? 1 : 0)))
				return false;
			else
				return true;
		}
	}
	
	/**
	 * 用于直接输出日期
	 */
	@Override
	public String toString() {
		return currentYear+"/"+currentMonth+"/"+currentDay+"/"+currentDayInWeek;
	}
	
	/**
	 * 以下为setter和getter.
	 */
	
	public void setDay(int day) throws IllegalDateException{
		this.tempDay = day;
		dayInWeekStandardize();
	}
	
	public void setMonth(int month) throws IllegalDateException{
		this.tempMonth = month;
		dayInWeekStandardize();
	}
	
	public void setYear(int year) throws IllegalDateException{
		this.tempYear = year;
		dayInWeekStandardize();
	}
	
	public void setTotal(int year, int month, int day) throws IllegalDateException{
		this.tempYear = year;
		this.tempMonth = month;
		this.tempDay = day;
		dayInWeekStandardize();
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public int getCurrentDayInWeek() {
		return currentDayInWeek;
	}
	/**
	 * 
	 * @param date 另一个“日期”(Date)
	 * @return 二者是否相等
	 */
	public boolean equals(Date date) {
		if((this.currentDay==date.currentDay)&&
		   (this.currentMonth==date.currentMonth)&&
		   (this.currentYear==date.currentYear))
			return true;
		else
			return false;
	}
	
	
	
}

class IllegalDateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
















