package io.github.golden_pigeon.calendar;
/**
 * ��������ڱ�ʾ�����Լ�����һЩ�й����ڵĻ�������
 * @author Golden_Pigeon
 *
 */
public class Date {
	/**
	 * currentXXX��ʾ��ǰ�����ڣ���ʼ��Ϊ2000��1��1��������
	 * tempXXX���ڴ����û��趨������
	 * ÿ���û��趨���ں�ִ��dayInWeekStandardize()�������ǡ���ǰ���ڡ�ǰ��/���˵��趨���ڣ�
	 * �����ͬʱ������û��趨���ڵ����ڡ�
	 * <�������>��plus/minusDay�ȷ������޸������ֶκ�Ҫע���ڷ�������ǰȷ����Ӧ�ֶ����
	 * �������ó�private�Ա����û�˽���޸��йر�����û�н������ڵı�׼����ɴ���
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
	 * ���췽��
	 * @param year �趨���
	 * @param month �趨�·�
	 * @param day �趨��
	 */
	public Date(int year, int month, int day) throws IllegalDateException{
		this.tempYear = year;
		this.tempMonth = month;
		this.tempDay = day;
		dayInWeekStandardize(); //ʹ��ǰ����ǰ��/���ˣ�����������
		
	}
	/**
	 * Ĭ�Ϲ��췽��
	 * ��ʼ��Ϊ2000��1��1��
	 */
	public Date() {
		this.tempYear = 2000;
		this.tempMonth = 1;
		this.tempDay = 1;
	}
	/**
	 * �жϵ�ǰ����Ƿ�Ϊ����
	 * @return �������򷵻�true,���򷵻�false.
	 */
	public boolean isLeap() {
		if((currentYear % 4 == 0)&&((currentYear % 100 != 0)||(currentYear % 400 == 0)))
			return true;
		else
			return false;
	}
	/**
	 * �жϵ�ǰ�·��Ƿ�Ϊ����
	 * @return �������򷵻�true,���򷵻�false.
	 */
	public boolean isLeapMonth() {
		if(isLeap()&&currentMonth == 2) // ֻ������Ķ��²ſ���������
			return true;
		else
			return false;
	}
	/**
	 * ���㵱ǰ�·�������
	 * @return ��ǰ�·�������
	 */
	public int maxDaysInCurrentMonth() {
		return daysPerMonth[currentMonth - 1] + (isLeapMonth() ? 1 : 0); //������������һ��
	}

	/**
	 * �ṩ���û�������ǰ������
	 * @param days ǰ��������
	 */
	public void plusDays(int days) {
		for(int i = 0; i < days; i++)
			plusDay();
		//ȷ��ÿ�β�����tempXXX��currentXXX��һ�µ�
		tempDay = currentDay;
		tempMonth = currentMonth;
		tempYear = currentYear;
	}
	/**
	 * �ײ㷽��������ǰ��һ�죬�����չ����λ��ͬʱ�������ڡ�
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
	 * plusDay�ĸ��������������·ݣ����������λ
	 */
	private void plusMonth() {
		if(currentMonth < 12)
			currentMonth++;
		else {
			//��λ
			currentMonth = 1;
			plusYear();
		}
	}
	/**
	 * plusMonth���������������·�
	 */
	private void plusYear() {
		currentYear++;
		if(currentYear == 0)
			currentYear++;
	}
	
	/**
	 * �ṩ���û������ں��˷���
	 * @param days ���˵�����
	 */
	public void minusDays(int days) {
		for(int i = 0; i < days; i++)
			minusDay();
		//ȷ��ÿ�β�����tempXXX��currentXXX��һ�µ�
		tempDay = currentDay;
		tempMonth = currentMonth;
		tempYear = currentYear;
	}
	/**
	 * ���ں���һ�죬������plusDay���ƣ�����׸����
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
	 * ʹ��ǰ����ǰ��/���ˣ�ֱ���û��趨���ڣ����������ڡ�
	 */
	private void dayInWeekStandardize() throws IllegalDateException {
		if(!isDateLegal())
			throw new IllegalDateException();
		int cmp = tempCmpCurrent(); //����cmp�ж��趨�������ڵ�ǰ����֮ǰ����֮���Ծ���ǰ��/����
		if(cmp == 0) // ����ǰ�������趨������ͬ����ִ���κβ���
			return;
		else if(cmp > 0) // ����ǰ����ֱ���趨���ڣ���ͬ
			while(tempCmpCurrent() > 0)
				plusDay();
		else
			while(tempCmpCurrent() < 0)
				minusDay();
	}
	/**
	 * �Ƚ��趨�������ڵ�ǰ����֮ǰ����֮��
	 * @return ��֮ǰ���ظ�ֵ����֮�󷵻���ֵ�����򷵻�0.
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
	 * ����ֱ���������
	 */
	@Override
	public String toString() {
		return currentYear+"/"+currentMonth+"/"+currentDay+"/"+currentDayInWeek;
	}
	
	/**
	 * ����Ϊsetter��getter.
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
	 * @param date ��һ�������ڡ�(Date)
	 * @return �����Ƿ����
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
















