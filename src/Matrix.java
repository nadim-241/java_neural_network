import java.util.ArrayList;
import java.util.List;

public class Matrix {
    public int rows;
    public int cols;

    public double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                data[i][j] = Math.random()*2-1;
            }
        }
    }

    public List<Double> toArray() {
        List<Double> temp = new ArrayList<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                temp.add(data[i][j]);
            }
        }
        return temp;
    }

    public void print() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                System.out.println(this.data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void add(double scalar){
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.data[i][j] += scalar;
            }
        }
    }

    public void add(Matrix m) {
        if(cols != m.cols || rows != m.rows) {
            System.out.println("shape mismatch!");
            return;
        }
        for(int i = 0; i < m.rows; i++) {
            for(int j = 0; j < m.cols; j++) {
                this.data[i][j] += m.data[i][j];
            }
        }
    }

    public void multiply(double scalar) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.data[i][j] *= scalar;
            }
        }
    }

    public void multiply(Matrix m) {
        for(int i = 0; i < m.rows; i++) {
            for(int j = 0; j < m.cols; j++) {
                this.data[i][j] *= m.data[i][j];
            }
        }
    }

    public void sigmoid() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
            }
        }
    }

    public Matrix dsigmoid() {
        Matrix temp = new Matrix(rows, cols);

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
            }
        }
        return temp;
    }

    public static Matrix fromArray(double[] array) {
        Matrix temp = new Matrix(array.length, 1);

        for(int i = 0; i < array.length; i++) {
            temp.data[i][0] = array[i];
        }
        return temp;
    }


    public static Matrix subtract(Matrix m1, Matrix m2) {
        Matrix temp = new Matrix(m1.rows, m1.cols);
        for(int i = 0; i < m1.rows; i++) {
            for(int j = 0; j < m1.cols; j++) {
                temp.data[i][j] = m1.data[i][j]-m2.data[i][j];
            }
        }
        return temp;
    }

    public static Matrix transpose(Matrix m) {
        Matrix temp = new Matrix(m.cols, m.rows);

        for(int i = 0; i < m.rows; i++) {
            for(int j = 0; j < m.cols; j++) {
                temp.data[j][i] = m.data[i][j];
            }
        }
        return temp;
    }

    public static Matrix dotProduct(Matrix m1, Matrix m2) {
        Matrix temp = new Matrix(m1.rows, m2.cols);
        for(int i = 0; i < temp.rows; i++) {
            for(int j = 0; j < temp.cols; j++) {
                double sum = 0;
                for(int k = 0; k < m1.cols; k++) {
                    sum += m1.data[i][k]*m2.data[j][k];
                }

                temp.data[i][j] = sum;
            }

        }
        return temp;
    }
}
