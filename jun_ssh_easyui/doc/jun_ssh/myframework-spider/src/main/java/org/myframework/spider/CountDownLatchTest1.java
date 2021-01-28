package org.myframework.spider;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class CountDownLatchTest1 {

	public CountDownLatchTest1() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void main( ) {
		final CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (final int i : new int[] { 1, 2, 3 }) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						threadSleep(i);
						System.out.println( "线程编号" +i + "===============");
					} finally {
						System.out.println("倒计时===============");
						latch.countDown();
					}
				}
			});
		}
		System.out.println(  "等待线程===============");
		latchAwait(latch);
		System.out.println(  "线程都结束了，主线程退出===============");
	}

	public ExecutorService getExecutorService() {
		return Executors.newCachedThreadPool();
	}

	private static void latchAwait(final CountDownLatch latch) {
		try {
			latch.await();
		} catch (final InterruptedException ex) {
			ex.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	private static void threadSleep(final int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (final InterruptedException ex) {
			ex.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

}
