package com.spotify.ouath2.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;

/**
 * @author Mihajlo Gorunovic (m.gorunovic@levi9.com)
 * @since $(Date)
 */

public class BaseTest {
	@BeforeMethod
	public void beforeMethod(Method m){
		System.out.println("STARTING TEST: " + m.getName());
		System.out.println("THREAD ID: " + Thread.currentThread().getId());

	}

}
