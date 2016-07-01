package by.trepam.multithreading.domain;

public class Matrix {

	int[][] matrix;

	public void setMatrix(int[][] m) {
		matrix = m;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public int[] getColumn(int index) {
		int length = matrix.length;
		int[] matr = new int[length];
		for (int i = 0; i < length; i++) {
			if (index < matrix[i].length) {
				matr[i] = matrix[i][index];
			} else {
				return null;
			}
		}
		return matr;
	}

	public int[] getRow(int index) {
		if (index < matrix.length) {
			return matrix[index];
		} else {
			return null;
		}
	}
	
	public int getLength() {
		return matrix.length;
	}
	
	public int getWidth() {
		return matrix[0].length;
	}

	public void setElement(int element, int row, int colomn) {
		matrix[row][colomn] = element;
	}
}
