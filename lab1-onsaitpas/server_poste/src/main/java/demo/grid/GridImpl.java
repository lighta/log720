package demo.grid;

/**
 * A very simple implementation of a 2-D grid
 */

import demo.grid.MyGridPackage.GridException;

public class GridImpl extends MyGridPOA {
	protected short height = 31;
	protected short width = 14;
	protected double[][] mygrid;

	public GridImpl() {
		mygrid = new double[height][width];
		for (short h = 0; h < height; h++) {
			for (short w = 0; w < width; w++) {
				mygrid[h][w] = 0.21;
			}
		}
	}

	public short height() {
		return height;
	}

	public short width() {
		return width;
	}

	public double getElement(short n, short m) {
		if ((n <= height) && (m <= width))
			return mygrid[n][m];
		else
			return (0.01);
	}

	public void setElement(short n, short m, double value) {
		if ((n <= height) && (m <= width))
			mygrid[n][m] = value;
	}

	public short opWithException() throws GridException {
		throw new GridException(
				"This is only a test exception, no harm done :-)");
	}
}
