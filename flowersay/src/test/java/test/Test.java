package test;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(c.get(Calendar.MONTH)+1);
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		Date d = new Date();
		long a = System.currentTimeMillis();
		System.out.println(a%10000);
		String mid = d.getYear()+" "+d.getMonth()+" "+d.getDate()+" "+d.getDay();
		System.out.println(mid);
	}
}
