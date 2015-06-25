/**
 * 
 * ACCTestJava - ACC Java Test Platform
 * Copyright (c) 2014, AfirSraftGarrier, afirsraftgarrier@qq.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.acc.test.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class ExecutorServiceTest {
	public static void excute(ExecutorService executorService, int excuteSize) {
		for (int i = 0; i < excuteSize; i++) {
			final int index = i;
			try {
				executorService.execute(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(1000);
							System.out.println("success:" + index);
						} catch (InterruptedException interruptedException) {
							System.out.println("InterruptedException:" + index);
						}
					}
				});
			} catch (RejectedExecutionException rejectedExecutionException) {
				System.out.println("RejectedExecutionException:" + index);
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
	}

	private static void shutdown(ExecutorService executorService) {
		System.out.println("shut down");
		executorService.shutdown();
	}

	private static void shutdownnow(ExecutorService executorService) {
		System.out.println("shut down now with rest size:"
				+ executorService.shutdownNow().size());
	}

	private static void test(ExecutorService executorService,
			boolean isShutdownnow, int size) {
		excute(executorService, size);
		if (isShutdownnow) {
			shutdownnow(executorService);
		} else {
			shutdown(executorService);
		}
		excute(executorService, size);
	}

	private static void testShutdown(ExecutorService executorService, int size) {
		test(executorService, false, size);
	}

	private static void testShutdownnow(ExecutorService executorService,
			int size) {
		test(executorService, true, size);
	}

	public static void main(String[] args) {
		System.out.println("Please input your test number:");
		int testNum = new java.util.Scanner(System.in).nextInt();
		switch (testNum) {
		case 1:
			testShutdown(Executors.newCachedThreadPool(), 5);
			break;
		case 2:
			testShutdownnow(Executors.newCachedThreadPool(), 5);
			break;
		case 3:
			testShutdownnow(Executors.newCachedThreadPool(), 1000);
			break;
		case 4:
			testShutdownnow(Executors.newFixedThreadPool(10), 1000);
			break;
		case 5:
			testShutdownnow(Executors.newSingleThreadExecutor(), 5);
			break;
		case 6:
			testShutdownnow(Executors.newFixedThreadPool(10), 1000);
			break;
		case 7:
			testShutdownnow(Executors.newFixedThreadPool(10), 1000);
			break;
		case 8:
			testShutdownnow(Executors.newFixedThreadPool(1), 1000);
			break;
		case 9:
			testShutdownnow(Executors.newSingleThreadExecutor(), 1000);
			break;
		case 10:
			break;
		}
	}

}