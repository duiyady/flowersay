package test;

import java.util.LinkedList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		list.add("a");
		list.add("b");
		for(String s : list) {
			System.out.println(s);
			if("a".equals(s)) {
				break;
			}
		}
	}
}
