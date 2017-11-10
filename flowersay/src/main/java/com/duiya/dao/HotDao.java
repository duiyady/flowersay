package com.duiya.dao;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.duiya.model.FlowerHot;

public class HotDao {
	public static FlowerLink head = new FlowerLink();
	public static int count = 0;
	public synchronized static void add(FlowerHot hot) {
		if(count == 0) {
			FlowerLink temp = new FlowerLink(hot);;
			head.next = temp;
			temp.pre = head;
			count++;
		}else {
			FlowerLink temp = head;
			boolean flag = false;
			while(temp.next != null) {
				temp = temp.next;
				if(temp.hot.equals(hot)) {
					temp.hot.add();
					flag = true;
					if(temp.hot.getCount() > 5) {
						temp.pre.next = temp.next;
						temp.next = head.next;
						head.next = temp;
						temp.pre = head;
					}
					break;
				}
			}
			if(flag == false) {
				if(count < 15) {
					FlowerLink temp2 = new FlowerLink(hot);
					temp.next = temp2;
					temp2.pre =temp;
					count++;
				}else {
					FlowerLink temp2 = new FlowerLink(hot);
					temp2.pre = temp.pre;
					temp.pre.next = temp2;
				}
			}
		}
		
	}

	public static List<Map<String,Object>> getHotFlower(){
		List<Map<String,Object>> list = new LinkedList<Map<String, Object>>();
		FlowerLink temp = head;
		while(temp.next != null) {
			temp = temp.next;
			list.add(temp.hot.getFlower());
		}
		return list;
	}
	
}
class FlowerLink{
	public FlowerLink next;//上一个
	public FlowerLink pre;//下个
	public FlowerHot hot;
	public FlowerLink(FlowerHot hot) {
		super();
		this.hot = hot;
		next = null;
	}
	public FlowerLink() {
		super();
	}
	
}
	
	
