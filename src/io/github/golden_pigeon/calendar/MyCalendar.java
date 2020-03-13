package io.github.golden_pigeon.calendar;
/**
 * ԭ��������
 * ����ִ�и�����������
 * ������Date��
 * @author Golden_Pigeon
 *
 */
public class MyCalendar {
	private Date date;
	public MyCalendar() {
		this.date = new Date();
	}
	/**
	 * ��ӡָ����ݵ�����
	 * @param year ָ�������
	 */
	public void printCalendarByYear(int year) throws IllegalDateException{
		for(int month = 1; month <= 12; month++)
			printCalendarByMonth(year, month);
	}
	/**
	 * ��ӡָ������ݼ��·ݵ�����
	 * @param year ָ�������
	 * @param month ָ�����·�
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
		System.out.println(month + "��");
		System.out.println("-------------------------------");
		System.out.println("����\t��һ\t�ܶ�\t����\t����\t����\t����");
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
		String weekDay = "��";
		switch(dayInWeek) {
		case 1:
			weekDay += "һ";
			break;
		case 2:
			weekDay += "��";
			break;
		case 3:
			weekDay += "��";
			break;
		case 4:
			weekDay += "��";
			break;
		case 5:
			weekDay += "��";
			break;
		case 6:
			weekDay += "��";
			break;
		default:
			weekDay += "��";
		}
		return weekDay;
	}
}
