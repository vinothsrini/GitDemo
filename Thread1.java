---->
Multi Threading:-

Introduction:
Two ways to define a Threading
	1)By extending Thread class
	2)By implementing Runnable(I)
3) Getting and setting Name of thread
4) Thread Priority
5) The method to prevent thread Execution
	1)yield()
	2)join()
	3)sleep()
6) Synchronization
7) InterThred Communication
8) DeadLock
9) Daemon threads
10) MultiThreading enhancements

Introduction:
************

Multitasking:-  Executing the multi tasks simultaneously

Executing several task simultaneously is a concept of multitasking. There are two types 
1)Process-based Multitasking(Multiprocessing)
2)Thread-based Multitasking(Multithreading)

Process-based Multitasking(Multiprocessing):-
--------------------------------------------
- Executing several tasks simultaneously where each task is a separate independent program(process) is 
called process based multitasking.

Ex:-
While Typing a java program in the editor we can listen audio songs from same system, at the same time we can download a file from net all
these tasks will be executed simultaneously and independent of each other hence it is process based multi tasking.

Process-based Multitasking is best suitable at OS level

Thread-based Multitasking(Multithreading):- 
------------------------------------------
- Executing several tasks simultaneously where each task is a separate independent part of the same program is called 
thread-based Multitasking and each independent part is called a thread.

Thread-based Multitasking is best suitable at programmatic level.


Whether it is Process or Thread based the main objective of Multi tasking is to reduce the response time of the system and to improve performance


The main important application areas of MultiThreading are 
1) To develop multimedia Graphics
2) To develop Animations
3) To develop video games
4) To develop web servers and application servers

When compared with old languages developing Multi Threading applications in JAVA is very easy bcoz JAVA provides inbuilt support for multi threading with rich API(Thread, Runnable, ThreadGroup,..}


Thread:-
---------

We can a define a Thread in the following two ways
	- By extending thread class
	- By Implementing Runnable Interface
	
By extending thread class:-
----------------------------

class Thread
{
	public void start()
	{
		register thread
		activity thread
		.
		.
		.
		run()
	}
}


csae 1:-

Thread Scheduler:-

It is the part of the JVM it is responsible to schedule threads ie if multiple threads are waiting to get chance of execution, then in which order threads will be executed is decided by thread scheduler.

We can't expect algorithm followed by thread scheduler it is varied from JVM to JVM hence we cannot expect thread execution order and exact output.

Hence whenever situation comes to multithreading there is no guarantee for exact output, but we can provide several possible outputs

class MyThreadk extends Thread
{
	public void run()
	{
		for(int i=0; i<10; i++)
			
		{
			System.out.println("child thread " + i);
		}
	}
}
public class StartThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyThreadk k = new MyThreadk();
		k.start();
		for(int i=0; i<10; i++)
			
		{
			System.out.println("Main thread " + i);
		}
	}

}


o/p:-

Main thread 0
child thread 0
child thread 1
child thread 2
child thread 3
Main thread 1
Main thread 2
Main thread 3
Main thread 4
child thread 4
child thread 5
child thread 6
Main thread 5
Main thread 6
Main thread 7
Main thread 8
Main thread 9
child thread 7
child thread 8
child thread 9



In the case of t.start() a new thread will be created which is responsible for the execution of run method.
But in the case of t.run() a new thread wont be created and run method will be executed just like a normal method call by main thread
Hence in above prgrm if we replace t.start() with t.run() then the o/p is

class MyThreadk extends Thread
{
	public void run()
	{
		for(int i=0; i<10; i++)
			
		{
			System.out.println("child thread " + i);
		}
	}
}
public class StartThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyThreadk k = new MyThreadk();
		k.run();
		for(int i=0; i<10; i++)
			
		{
			System.out.println("Main thread " + i);
		}
	}

}


o/p:-
child thread 0
child thread 1
child thread 2
child thread 3
child thread 4
child thread 5
child thread 6
child thread 7
child thread 8
child thread 9
Main thread 0
Main thread 1
Main thread 2
Main thread 3
Main thread 4
Main thread 5
Main thread 6
Main thread 7
Main thread 8
Main thread 9


 These total o/p is produced by main thread.

Case 3:-

Importance of thread class Start method, it is responsible to register the thread with thread scheduler and all other mandatory activities hence 
without executin the start method there is no chance of starting new thread in java due to this thread class start method is consider as heart of multi threading.


+Case 4:-
----------

Overloading of Run method

- Overloading of run method is always possible but thread class start method can invoke no argument run method the other overloaded 
method we have to call explicitly like a normal method call


Ex:1-
------
class MyThread extends Thread
{
	public void run()
	{
		System.out.println("no arg run");
	}
	

	public void run(int i)
	{
		System.out.println("1- arg run");
	}
}

public class Thread1 {
	
	public static void main(String[] args)
	{
		MyThread t = new MyThread();
		t.start();
		
	}

}

o/p:-
	no arg run


+case 5:-
----------

If we are not overriding run method, then thread class run method will be executed which has empty implementation hence we wont get any o/p

Note:- It is highly recommended to override run method otherwise don't go for multi threading concept.

class MyThread1 extends Thread
{
	
}

public class Thread2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread1 t = new MyThread1();
		t.start();
	}

}


+Case 6:-  Over riding of start method
---------------------------------------

If we override start method then our start method will be executed just like a normal method call and new thread wont be created 

Note: It is not recommended to override the start method otherwise don't go for multi threading concept.

Ex:1-
----
class MyThread3 extends Thread
{
	public void start()
	{
		System.out.println("start method");
	}
	
	public void run()
	{
		System.out.println("run method");
	}
	
}

public class Thread3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyThread3 t = new MyThread3();
		t.start();
		System.out.println("main method");
	}

}

o/p:-

start method
main method

+Case 7:-  Over riding of start method -1
---------------------------------------

Ex:-1
-----

class MyThread4 extends Thread
{
	public void start()
	{
		super.start();							// calling Thread class start method
		System.out.println("start method");		// wil be executed by main thread
	}
	
	public void run()
	{
		System.out.println("run method");		// will be executed by child thread
	}
	
}

public class Thread4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyThread4 t = new MyThread4();
		t.start();
		System.out.println("main method");		// wil be executed by main thread
	}

}

o/p:- possibilities

start method
main method
run method


run method
start method
main method

start method
run method
main method



Case 8:- Thread life cycle
--------------------------

1)New/Born(MyThread t = new MyThread)			--> t.start()

2)Ready/Runnable								--> If Thread scheduler allocate processing

3)Running										--> If run() method completed enter into 

4) Dead


Case 9:-
--------

After starting a thread if we trying to re-start the same thread then we will get runtime exception illegalStateThreadException

Thread t = new Thread();
t.start();
.
.
.
t.start();

o/p:-
-----

R.E: illegalStateThreadException



--------------------------------------------------------------------------------------------------------------------------------------------------------------


We can define a thread by implementing Runnable interface

MyThread  		--> Thread  		-->Runnable(I)

MyRunnable  	-->Runnable(I)

- Runnable Interface present in java.lang.package
- It contain only one method  (public void Run())



Ex:2-

class MyRunnable implements Runnable
{
	public void run()
	{
		for(int i=0; i<10; i++)
		{
			System.out.println("child thread");
		}
	}
	
}
public class Thread5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyRunnable r = new MyRunnable();
		Thread t = new Thread(r);			//target runnable
		t.start();
		

		for(int i=0; i<10; i++)
		{
			System.out.println("main thread");
		}

	}

}


o/p: (possibilities)

main thread
main thread
main thread
main thread
main thread
main thread
main thread
main thread
main thread
child thread
child thread
child thread
child thread
child thread
main thread
child thread
child thread
child thread
child thread
child thread





Case Study:-

MyRunnable r = new MyRunnable();
Thread t1 = new Thread();
Thread t2 = new Thread(r);

1) t1.start();	 			--> a new thread will be created which is responsible for the execution of Thread class run method which has empty implementation
2) t1.run();				--> no new thread will be created and Thread class run method will be executed just like a normal method call
3) t2.start();				--> a new thread will be created which is responsible for the execution of MyRunnable class run method
4) t2.run();				--> no new thread will be created and MyRunnable class run method will be executed just like a normal method call
5) r.start();				--> C.E - cannot find symbol method Start() location class My Runnable();
6) r.run()					--> no new thread will be created and MyRunnable class run method will be executed just like a normal method call



Defining a thread:-
********************

1) Approach 1:-  class MyThread extends Thread
2) Approach 2:-  class MyRunnable implements Runnable

Which approach is best to define a thread?

Among two way of defining a thread implement runnable approach is recommended.
In the first approach our class always extends thread class, there is no chance of extending any other class hence we are missing inherited benefit.
But in the second approach while implementing runnable interface we can extend any other class hence we wont miss any inheritance benefit.


Because of above reason implementing runnable interface approach is recommended than extending thread class 



Thread class Constructors:-
***************************

1) Thread t = new Thread();
2) Thread t = new Thread(Runnable r);
3) Thread t = new Thread(String name);
4) Thread t = new Thread(Runnable r, String name);
5) Thread t = new Thread(ThreadGroup g, String name);
6) Thread t = new Thread(ThreadGroup g, Runnable r);
7) Thread t = new Thread(ThreadGroup g, Runnable r, String name);
8) Thread t = new Thread(ThreadGroup g, Runnable r, String name, long Stacksize);


Getting and Setting a name of a thread:-
----------------------------------------
Every thread in Java has some name it may be default generated by JVM or customized name provided by programmer

We can get and set name of a thread by using the following two method of thread class
	public final String getName()
	public final void setName(String name)

Note:-

- We can get current executing thread oBject by using "Thread.currentThread()"
	


class MyThread7 extends Thread
{
	
	
}
public class Thread7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(Thread.currentThread().getName());
		MyThread7 t = new MyThread7();
		System.out.println(t.getName());
		Thread.currentThread().setName("super star");
		System.out.println(Thread.currentThread().getName());
		System.out.println(10/0);
	}

}

o/p:-

main
Thread-0
super star
Exception in thread "super star" java.lang.ArithmeticException: / by zero
	at com.thread.Thread7.main(Thread7.java:19)



Ex-2:-
-------


class MyThread8 extends Thread
{
	public void run()
	{
		System.out.println("run method Executed by " + Thread.currentThread().getName());
	}
	
}
public class Thread8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyThread8 t = new MyThread8();
		t.start();
		System.out.println("main method Executed by " + Thread.currentThread().getName());

	}

}



o/p:-

main method Executed by main
run method Executed by Thread-0





Thread Priorities:-
*******************

Every Thread in Java has some priority it may be default priority generated by JVM or customized priority provided by program. The valid range of thread priorities are is 1-10.

Thread Priorities starts from 1-10

Priority 1 		--> Minimum priority
Priority 5		--> Normal priority
Priority 10 	--> Maximum Priority

Thread class defines the following constants to represent some standard priorities
		Thread.MIN_PRIORITY			--> 1
		Thread.NORM_PRIORITY		--> 5
		Thread.MAX_PRIORITY			--> 10
		

Thread scheduler will use priorities while allocating processor. 
The thread which is having highest priorities will get chance first. Suppose if two thread having same priority then we cant exact expect exact execution order,  it depend on Thread scheduler.

Thread class defines the following method to get and set priority of a thread.
	
	1) public final int getPriority()
	2) public final void setPriority(int p)   // allowed values range for p is 1-10 otherwise we will get R.E as illegalArgumentException.
	
The default priority only for the main thread is 5 but for all remaining threads default priority will be inherited from parent to child ie whatever priority parent thread has the same priority will be there for the child thread



class MyThread9 extends Thread
	{
	
	}
public class Thread9 {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(Thread.currentThread().getPriority());
		
		
		
		Thread.currentThread().setPriority(7);
		MyThread9 t1 = new MyThread9();
		t1.start();
		System.out.println(t1.getPriority());
		System.out.println(Thread.currentThread().getPriority());
		System.out.println(t1.getPriority());
	}

}

o/p:-

5
7
7
7

Ex:12

class MyThread10 extends Thread
{
	public void run()
	{
		for(int i=1; i<10; i++)
		{
			System.out.println(i);
		}
	}
}
public class Threads10 {

	public static void main(String[] args)
	{
		MyThread10 t = new MyThread10();
		t.setPriority(7);
		t.start();
		//System.out.println(Thread.currentThread().getPriority());
		for(int j=100; j<110; j++)
		{
			System.out.println(j);
		}
	}
}


Prevention of Thread Execution:-

We can prevent the thread execution by using the following methods
		1) yield()
		2) join()
		2) sleep()
	
	 
yield() Method:-
-------------

- yield method causes to pause current executing thread to give the chance for waiting threads of same priority, if there is no waiting thread or all waiting threads have low priority then same thread can continue its execution

- If multiple threads are waiting with same priority then which waiting thread will get the chance we cant the expect it depends on thread scheduler

- The thread which is yielded, when it will get chance once again it depends on thread scheduler and we cant expect exactly.

Note: Some platform wont provide support for yield method 

public static native void yield()


Ex-

class MyThread3 extends Thread
{
	public void run()
	{
		for(int i=0; i<10; i++)
		{
			System.out.println(i);
			Thread.yield();
		}
	}
}
public class Thread1 {
	
	public static void main(String[] args)
	{
		MyThread3 t = new MyThread3();
		t.start();
		for(int i=10; i<20; i++)
		{
			System.out.println(i);
		}
	}

}



Join:-
------

If a thread a wants to wait until completing some other thread then we should go for "join" method

For example if a thread t1 wants to wait until completing thread t2 then t1 has to call "t2.join()"
If t1 executes t2.join() then immediately t1 will be entered into waiting state until t2 completes
Once t2 completes then t1 can continue its execution.

Constructors:-
--------------
public final void join() throws InterruptException

public final void join(long ms) throws IE

public final void join(long ms, int ns) throws IE

Note:- Every join method throws interrupted exception which is checked exception hence compulsory we should handle this exception either by using try catch or by throws keyword.
       Otherwise we will get compile time error.


Impact of join method:

1) If t2 completes or
2) If time expires
3) If waiting thread got interrupted


Case 2:-

Waiting of child thread until completing main thread

class MyThreads14 extends Thread
{
	static Thread k;
	public void run()
	{
		
		try {
			k.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<10; i++)
		{
			System.out.println("child thread");
		}
	}
}
public class Threads14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyThreads14 t = new  MyThreads14();
		t.start();
		MyThreads14.k = Thread.currentThread();
		for(int i=0; i<10; i++)
		{
			System.out.println("main thread");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}



+Case 3:-

If main Thread called join method on thread child object and child thread calls join method on main thread object then both thread will wait forever and the program will be struck . This is called as Deadlock 

class MyThreads14 extends Thread
{
	static Thread k;
	public void run()
	{
		
		try {
			k.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<10; i++)
		{
			System.out.println("child thread");
		}
	}
}
public class Threads14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyThreads14 t = new  MyThreads14();
		MyThreads14.k = Thread.currentThread();
		t.start();
		try {
			t.join();									//deadlock
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i=0; i<10; i++)
		{
			System.out.println("main thread");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}



Case 4:-

If a thread calls join method on the same thread itself then the program will be struck . This is called as Deadlock

Note: In this case thread has to wait infinite amount of time.


Ex-1;

public class Threads15 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread.currentThread().join();
	}

}




Sleep Method:-
******************************************************

If a Thread don't want to perform any operation for a particular amount of time, then we should go for sleep method.

public static native void sleep(long ms)      			//we need include "throws declaration" for sleep method
public static void sleep(long ms, int nanoseconds)

Note: Every sleep method throws interrupted exemption, which is checked exemption  hence whenever we are using sleep method compulsory we should handle Interrupted exemption either by try catch or by throws keyword 
otherwise we will get compile time error.


Exemption:-

- If time expires or 
- If sleeping thread got interrupted


How a Thread can interrupt another Thread:-
-------------------------------------------

- A thread can interrupt a sleeping thread or waiting thread by using Interrupt method of Thread class

public void interrupt();

Note: When we are calling interrupt method if the target Thread no in sleeping state or waiting state then there is no impact of interrupt call immediately.
	  Interrupt call will be waited until target Thread entered into sleeping or waiting state. If the target thread enetered into sleeping or waiting state then
      immediately.
 	  
If a target thread never entered into sleeping state or waiting state in its life time then there is no impact of interrupt call, this is the only case where interrupt call getting wasted



Property	
yield()	
join()
sleep()
Purpose
If a thread want to pause it execution to give the chance of remaining threads of same priority then we should go for yield() method	
If a threads wants to wait until completing some other thread then we should go for join method
If a thread don't want to perform any operation for  certain time , then we should go for sleep method
Is it overloaded?
No
yes
yes

Is it Final?	
No
yes
No
Is it throws IE?	
No
yes
yes
Is it native?	
yes
No
sleep(long ms) --->native

sleep(long ms, int ns)	---> non native
Is it Static?	
yes
No
yes





5/2-1

Synchronization:
----------------

Synchronized is the modifier applicable only for the methods and blocks but not for the classes and variables. If multiple threads are trying to operate simultaneously and the same java object
then there may be a chance of data inconsistency problem.

To overcome this problem we should go for synchronized keyword if a method or block declared as synchronized then at a time only one thread is allowed to execute that method or block and given objects
so that data inconsistency problem will be resolved.

Advantage:
- we can resolve or overcome data inconsistency problem

Disadvantages: 
- It increases the waiting time of threads and creates performance problem hence if there is no specific requirements then its not recommended to use synchronized keyword



Internally synchronized concept is implemented by using lock , every object in java has a unique lock whenever we are using synchronized keyword then only lock will come into the picture.

If a thread wants to execute synchronized method on the given object, first it has to get lock of that object, once thread got the lock then it is allowed to execute any synchronized method on that object.
once method execution completes automatically thread releases the lock 

Acquiring and releasing lock internally take care by JVM and programmer not responsible for these activities





