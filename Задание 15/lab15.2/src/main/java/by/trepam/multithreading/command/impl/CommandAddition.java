package by.trepam.multithreading.command.impl;

import by.trepam.multithreading.command.Command;

public class CommandAddition implements Command{
	
		private double[] in;
		private double out;
	
		public void setInput(double[] in) {
			this.in=in;
			
		}
	
		public void execute() {
			out=0.0;
			for(double element:in){
				out+=element;
			}
			
		}
	
		public double getResult() {
			return out;
		}


}
