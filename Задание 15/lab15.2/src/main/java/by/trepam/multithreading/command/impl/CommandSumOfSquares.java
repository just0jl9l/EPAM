package by.trepam.multithreading.command.impl;

import by.trepam.multithreading.command.Command;

public class CommandSumOfSquares  implements Command{
	
		private double[] in;
		private double out;
	
		public void setInput(double[] in) {
			this.in=in;
			
		}
	
		public void execute() {
			out=0.0;
			for(double element:in){
				out+=Math.pow(element,2);
			}
			
		}
	
		public double getResult() {
			return out;
		}

}