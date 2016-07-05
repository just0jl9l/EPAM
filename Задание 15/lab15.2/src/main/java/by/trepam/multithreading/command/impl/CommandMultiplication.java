package by.trepam.multithreading.command.impl;

import by.trepam.multithreading.command.Command;

public class CommandMultiplication implements Command{
	
		private double[] in;
		private double out;
	
		public void setInput(double[] in) {
			this.in=in;
			
		}
	
		public void execute() {
			out=1.0;
			for(double element:in){
				out*=element;
			}
			
		}
	
		public double getResult() {
			return out;
		}

}
