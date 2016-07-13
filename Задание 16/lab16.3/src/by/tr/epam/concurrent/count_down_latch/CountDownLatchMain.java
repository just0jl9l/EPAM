package by.tr.epam.concurrent.count_down_latch;

/* Скачки. Создано 5 лошадей. После сигнала о начале скачек они преодаливают некоторую дистанцию. Скачки считаются
 * оконченными, когда все лошади достигнут конца дистанции.
 */
public class CountDownLatchMain {
	public static void main(String[] args)
	        throws InterruptedException, java.io.IOException
	    {	        
	        Race r = new Race(
	            "Лошадь 1",
	            "Лошадь 2",
	            "Лошадь 3",
	            "Лошадь 4",
	            "Лошадь 5"
	            );
	        
	        System.out.println("Заезд с дистанцией " + r.getDistance() + " пунктов");
	        
	        r.run();
	    }
}
