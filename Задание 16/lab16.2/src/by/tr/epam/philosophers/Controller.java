package by.tr.epam.philosophers;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Controller {
	private final static int WORK_TIME = 100; //врем€, поко главный поток спит, а дочерние (философы) выполн€ютс€
	private final static int WAIT_TIME = 50;	//врем€, которое главный поток ждЄт, пока завершаютс€ дочерние потоки
    private final List<Philosopher> philosophers = new ArrayList<>();	//список всех философов
    private final int n;					//количество философов (и количество вилок)
    private final long THINK_TIME = 1L;		//врем€, которое один филосов тратит на размышление
    private final long EAT_TIME = 1L;		//врем€, которое один филосов тратит на один приЄм пищи

    public Controller(int n) {				
        this.n = n;							//установка числа философов
    }

    public void newRun() {
        Semaphore waiter = new Semaphore(n - 1);	// создаЄтс€ семафор с возможностью захватить
													// его в некоторый момент времени всеми философами,
													// кроме одного
        
        List<Fork> forks = new ArrayList<>();		//создаЄтс€ список вилок
        for (int i = 0; i < n; i++) {			//в список добавл€етс€ столько же вилок, сколько и философов					
            forks.add(new Fork(i));				//каждой вилке устанавливаетс€ id, соответствующий номеру в списке
        }
        for (int i = 0; i < n; i++) {			//создаЄтс€ n философов с установлением каждому "правой" и "левой" вилок
            Fork left = forks.get(i);			//"правой" считаетс€ вилка, соответствующа€ номеру философа
            Fork right = forks.get((i + 1) % n);	//левой устанавливаетс€ следующа€ вилка (дл€ всех на 1 больше номера,
            										//кроме последнего, дл€ него "левой" будет перва€ вилка (т.е. с номером 0)
            
            philosophers.add(new Philosopher(waiter,THINK_TIME,EAT_TIME, left, right, i)); //создаЄтс€ филосов с 
            															//соответствующими вилками, семафором и id 
        }
        philosophers.forEach(Thread::start);	//все потоки (философы) запускаютс€
        Util.waitMillis(WORK_TIME);			//главный поток спит
        philosophers.forEach(Thread::interrupt);	//все потоки (философы) прерываютс€
        Util.waitMillis(WAIT_TIME);		//главный поток спит
    }

}