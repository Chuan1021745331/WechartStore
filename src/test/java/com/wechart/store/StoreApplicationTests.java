package com.wechart.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class StoreApplicationTests {

	@Test
	public void contextLoads() {
		List<String> studentList=new ArrayList<>();
		studentList.add("zhangsan");
		studentList.add("lisi");
		studentList.add("xiaoming");
		for (String s : studentList) {
			System.out.println(s);
		}
		System.out.println("");
	}

}
