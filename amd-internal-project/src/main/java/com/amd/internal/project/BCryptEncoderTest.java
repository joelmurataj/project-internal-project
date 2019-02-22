package com.amd.internal.project;

import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
//		for(int i=0;i<=10;i++) {
//			String encodedString=encoder.encode("super");
//			System.out.println(encodedString);
//		}
		HashMap<Integer, String> hash = new HashMap<Integer, String>();
		hash.put(1, "joel");
		hash.put(2, "joel2");
		hash.put(23, "joel23");
		hash.put(4, "joel4");
		hash.put(13, "joel13");
		hash.put(10, "joel10");
		for(Integer key : hash.keySet()) {
			System.out.println(hash.get(key));
		}
	}

}
