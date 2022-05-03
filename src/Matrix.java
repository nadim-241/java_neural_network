import java.util.ArrayList;
import java.util.List;

public class Matrix {
    double[][] data;
    int rows, cols;

    public Matrix(int rows, int cols) {
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        //initialises the matrix with random values between 1.0 and -1.0
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                data[row][col] = Math.random()*2-1;
            }
        }
    }

    public void print() {
        for(double[] row : this.data) {
            for(double d : row) {
                System.out.println(d + " ");
            }
            System.out.println();
        }
    }

    public void add(double scalar) {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                data[row][col]+=scalar;
            }
        }
    }

    public void add(Matrix matrix) {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                this.data[row][col]+=matrix.data[row][col];
            }
        }
    }

    public static Matrix fromArray(double[] d) {
        Matrix temp = new Matrix(d.length, 1);
        for(int row = 0; row < d.length; row++) {
            temp.data[row][0] = d[row];
        }
        return temp;
    }

    public List<Double> toArray() {
        ArrayList<Double> temp = new ArrayList<>();
        for(double[] row : data) {
            for(double d : row) {
                temp.add(d);
            }
        }
        return temp;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix temp = new Matrix(a.rows, a.cols);
        for(int row = 0; row < a.rows; row++) {
            for(int col = 0; col < a.cols; col++) {
                temp.data[row][col] = a.data[row][col] - b.data[row][col];
            }
        }
        return temp;
    }

    public static Matrix transpose(Matrix a) {
        Matrix temp = new Matrix(a.rows, a.cols);
        for(int row = 0; row < a.rows; row++) {
            for(int col = 0; col < a.cols; col++) {
                temp.data[col][row] = a.data[row][col];
            }
        }
        return temp;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp = new Matrix(a.rows, a.cols);
        for(int row = 0; row < temp.rows; row++) {
            for(int col = 0; col < temp.cols; col++) {
                double sum = 0;
                for(int i = 0; i < a.cols; i++) {
                    sum+=a.data[row][i]*b.data[i][col];
                }
                temp.data[row][col] = sum;
            }
        }
        return temp;
    }

    public void multiply(Matrix a) {
        for(int row = 0; row < a.rows; row++) {
            for(int col = 0; col < a.cols; col++) {
                this.data[row][col] *= a.data[row][col];
            }
        }
    }

    public void multiply(double a) {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                this.data[row][col] *= a;
            }
        }
    }

    public void sigmoid() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                this.data[row][col] = 1/(1+Math.exp(-this.data[row][col]));
            }
        }
    }

    public Matrix dsigmoid() {
        Matrix temp = new Matrix(rows, cols);
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                temp.data[row][col] = temp.data[row][col] * (1-this.data[row][col]);
            }
        }
        return temp;
    }

}
