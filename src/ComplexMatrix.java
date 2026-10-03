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
}