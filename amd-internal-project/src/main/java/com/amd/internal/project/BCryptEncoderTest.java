package com.amd.internal.project;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//		for(int i=0;i<=10;i++) {
//			String encodedString=encoder.encode("super");
//			System.out.println(encodedString);
//		}
		ArrayList<String> users = new ArrayList<String>();
		users.add("java");
		users.add("html");
		users.add("angular");
		String lists = "";
		for (int i = 0; i < users.size() - 1; i++) {
			lists += users.get(i) + ", ";
		}
		if (!users.isEmpty()) {
			lists += users.get(users.size() - 1);
		}
		System.out.println(lists);
	}

}
