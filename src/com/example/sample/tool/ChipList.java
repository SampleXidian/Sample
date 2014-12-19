package com.example.sample.tool;

import java.util.LinkedList;
import java.util.List;

public class ChipList {

	List<String> list;
	int position = 0;

	public ChipList() {
		list = new LinkedList<String>();
	}

	public ChipList(List<String> list) {
		this.list = list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String next() {
		return list.get(position);
	}
}
