package lock;

public class DeadLock {

	public static void main(String[] args) {
		
		Lock1 l1 = new Lock1();
		Lock2 l2 = new Lock2();
		
		Thread t1 = new Thread("t1") {
			@Override
			public void run() {
				System.out.println("t1 l1");
				//同步代码块先获取lock1的对象锁
				synchronized (l1) {
					try {
						l1.sayHi();
						//睡眠50ms 防止线程1启动一下子就连续获得了lock1和lock2两个对象的对象锁
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//然后接着获取lock2的对象锁
					synchronized (l2) {
						l2.sayHi();
					}
				}
				System.out.println("t1 ok");
				super.run();
			}
		};
		
		Thread t2 = new Thread("t2") {
			@Override
			public void run() {
				System.out.println("t2 l2");
				synchronized (l2) {
					l2.sayHi();
					synchronized (l1) {
						l1.sayHi();
					}
				}
				System.out.println("t2 ok");
				super.run();
			}
		};
		
		t1.start();
		t2.start();
		
	}

}
