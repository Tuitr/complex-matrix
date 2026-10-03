public class ComplexMatrix {
    private ComplexNumber[][] matrix;
    private int rows;
    private int cols;

    //конструктор
    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new ComplexNumber[rows][cols];
    }

    //метод для записи числа в определенную позицию
    public void setValue(int row, int col, ComplexNumber value) {
        matrix[row][col] = value;
    }

    //метод для получения числа из определенной позиции
    public ComplexNumber getValue(int row, int col) {
        return matrix[row][col];
    }

    //метод для суммирования двух матриц
    public ComplexMatrix additionm(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы не соответствуют требованиям для операции сложения. Матрицы должны быть одной размерности.");
        }
        ComplexMatrix add = new ComplexMatrix(rows, cols);//создание новой локальной матрицы
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                add.setValue(i, j, this.getValue(i, j).additionn(other.getValue(i, j)));
            }
        }
        return add;
    }

    //метод для вычитания двух матриц
    public ComplexMatrix subtractm(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы не соответствуют требованиям для операции вычитания. Матрицы должны быть одной размерности.");
        }
        ComplexMatrix sub = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sub.setValue(i, j, this.getValue(i, j).subtractn(other.getValue(i, j)));
            }
        }
        return sub;
    }

    //метод умножения двух матриц
    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Матрицы не соответствуют требованиям для операции умножения. Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
        ComplexMatrix mult = new ComplexMatrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                ComplexNumber count = new ComplexNumber(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    count = count.additionn(this.getValue(i, k).multiply(other.getValue(k, j))); //считается одно значение
                }
                mult.setValue(i, j, count);
            }
        }
        return mult;
    }


    //метод для транспонирования матрицы
    public ComplexMatrix transposition() {
        ComplexMatrix trans = new ComplexMatrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                trans.setValue(j, i, this.getValue(i, j));
            }
        }
        return trans;
    }

    private ComplexMatrix minor(int row, int col) {
        ComplexMatrix m = new ComplexMatrix(rows - 1, cols - 1);
        for (int i = 0, mi = 0; i < rows; i++) {
            if (i == row) continue;
            for (int j = 0, mj = 0; j < cols; j++) {
                if (j == col) continue;
                m.setValue(mi, mj++, matrix[i][j]);
            }
            mi++;
        }
        return m;
    }

    //метод для нахождения детерминанта (любая квадратная матрица, разложение по первой строке)
    public ComplexNumber determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
        if (rows == 0) {
            return new ComplexNumber(1, 0);
        }
        ComplexNumber sum = new ComplexNumber(0, 0);
        for (int j = 0; j < cols; j++) {
            ComplexNumber term = matrix[0][j].multiply(minor(0, j).determinant());
            sum = (j % 2 == 0) ? sum.additionn(term) : sum.subtractn(term);
        }
        return sum;
    }

    //метод для вычисления обратной матрицы: adj(A) / det
    public ComplexMatrix inverse() {
        ComplexNumber det = determinant();
        if (Math.hypot(det.getReal(), det.getImaginary()) < 1e-12) {
            throw new ArithmeticException("Матрица вырождена, обратной не существует");
        }
        ComplexMatrix res = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ComplexNumber cof = minor(i, j).determinant();
                if ((i + j) % 2 != 0) {
                    cof = new ComplexNumber(0, 0).subtractn(cof);
                }
                res.setValue(j, i, cof.divide(det));
            }
        }
        return res;
    }

    //метод деления матриц: A / B = A * B^(-1)
    public ComplexMatrix divide(ComplexMatrix other) {
        return this.multiply(other.inverse());
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output.append(matrix[i][j]).append("\t");
            }
            output.append("\n");
        }
        return output.toString();
    }
}
