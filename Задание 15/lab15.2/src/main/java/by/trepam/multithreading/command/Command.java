package by.trepam.multithreading.command;

public interface Command {
	void setInput(double[] in);
	void execute();
	double getResult();
}
