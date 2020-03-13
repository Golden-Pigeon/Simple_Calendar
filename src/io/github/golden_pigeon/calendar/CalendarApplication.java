package io.github.golden_pigeon.calendar;
import java.util.Scanner;

public class CalendarApplication {
	private final String TITLE = "A Simple Calendar By Golden Pigeon\n";
	private final String LOGO = "================================================\n"
							  + "            GGGGG             PPPP      ©       \n"
							  + "           GG                 PP  PP            \n"
							  + "          GG                  PP    PP          \n"
							  + "          GG   GGGG           PP  PP            \n"
							  + "           GG   GG            PPPP              \n"
							  + "            GG GG             PP                \n"
							  + "             GGG              PP                \n"
							  + "                              PP                \n"
							  + "================================================\n";
	
	private final String MENU = "Menu:\n"
			                  + "\t1. 查询指定日期是星期几\n"
			                  + "\t2. 输出指定年份的日历\n"
			                  + "\t3. 退出\n"
			                  + "输入对应的数字并回车以选择功能\n";
	private final String INSTRUCTION_1 = "请输入日期（示例：2000 1 1）:\n";
	private final String INSTRUCTION_2 = "请输入年份（示例：2000）:\n";
	private final String ERROR_LOG = "输入非法，请重新输入\n";
	private Scanner sc;
	private MyCalendar mc;
	public void run() {
		mc = new MyCalendar();
		System.out.print(TITLE + LOGO + MENU);
		sc = new Scanner(System.in);
		String instr;
		String weekDay;
		int i;
		int year = 0, month = 0, day = 0;
menu:	while(true) {
			instr = sc.nextLine();
			try {
				i = Integer.parseInt(instr);
			}catch(Exception e) {
				System.out.print(ERROR_LOG);
				continue menu;
			}
func:		while(true) {
				if(i == 1) {
					System.out.print(INSTRUCTION_1);
					try {
						year = sc.nextInt();
						month = sc.nextInt();
						day = sc.nextInt();
					}catch(Exception e) {
						sc.nextLine();
						System.out.print(ERROR_LOG);
						continue func;
					}
					if(!sc.nextLine().equals("")) {
						System.out.print(ERROR_LOG);
						continue func;
					}
					try {
						weekDay = mc.getDayInWeek(year, month, day);
					}catch(Exception e) {
						System.out.print(ERROR_LOG);
						continue func;
					}
					System.out.print(year+"年"+month+"月"+day+"日是"+weekDay+"\n");
					System.out.println("按任意键继续...");
					sc.nextLine();
					System.out.print(MENU);
					continue menu;
				}
				else if(i == 2) {
					System.out.print(INSTRUCTION_2);
					try {
						year = sc.nextInt();
					}catch(Exception e) {
						sc.nextLine();
						System.out.print(ERROR_LOG);
						continue func;
					}
					if(!sc.nextLine().equals("")) {
						System.out.print(ERROR_LOG);
						continue func;
					}
					try {
						mc.printCalendarByYear(year);
					}catch(Exception e) {
						System.out.print(ERROR_LOG);
						continue func;
					}
					System.out.println("按任意键继续...");
					sc.nextLine();
					System.out.print(MENU);
					
					continue menu;
				}
				else if(i == 3) {
					sc.close();
					System.exit(0);
				}
				else {
					System.out.print(ERROR_LOG);
					continue menu;
				}
			}
			
		}
	}
	
}
