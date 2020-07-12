package week1.exam;

import algs4.StdRandom;

/**
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class Percolation {

    private int maxIndex;
    private boolean[][] openSite;
    private boolean[][] fullSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        maxIndex = n - 1;
        openSite = new boolean[n][n];
    }

    private void validateBoundary(int row, int col) {
        if ((row > maxIndex || row < 0) || (col > maxIndex || col < 0)) {
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateBoundary(row, col);
        openSite[row][col] = true;

        //todo
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateBoundary(row, col);
        return openSite[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateBoundary(row, col);

        //A full site is an open site that can be connected to an open site in the top row via a chain of neighboring
        // (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row.
        return fullSite[row][col];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i <= maxIndex; i++) {
            for (int j = 0; j <= maxIndex; j++) {
                if (openSite[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        //todo
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

        // generate open sites, if it percolates then generate a new one
        int size = 20;
        int row =  StdRandom.uniform(size);
        int col =  StdRandom.uniform(size);
    }
}
