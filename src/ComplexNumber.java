//класс для представления комплексного числа
class ComplexNumber {
    //действительная и мнимая части
    private double real;
    private double imaginary;

    //конструктор для комплексного числа
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    //метод получения действительной части комплексного числа
    public double getReal() {
        return real;
    }

    //метод получения мнимой части комплексного числа
    public double getImaginary() {
        return imaginary;
    }

    //метод сложения двух комплексных чисел
    public ComplexNumber additionn(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    //метод вычитания двух комплексных чисел
    public ComplexNumber subtractn(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    //метод для умножения двух комплексных чисел
    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(this.real * other.real - this.imaginary * other.imaginary, this.real * other.imaginary + this.imaginary * other.real);
    }

    //метод для деления двух комплексных чисел
    public ComplexNumber divide(ComplexNumber other) {
        double d = other.real * other.real + other.imaginary * other.imaginary;
        if (d == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return new ComplexNumber((this.real * other.real + this.imaginary * other.imaginary) / d,
                (this.imaginary * other.real - this.real * other.imaginary) / d);
    }

    //метод для вывода комплексного числа, доп проверка на положительную/отрицательную мнимую часть
    @Override
    public String toString() {
        if (real != 0 && imaginary != 0) {
            if (imaginary > 0){
                return real + "+" + imaginary + "i";
            }
            return real + "" + imaginary + "i";
        } else if (real == 0 && imaginary != 0) {
            return imaginary + "i";
        } else if (real != 0 && imaginary == 0) {
            return real + "";
        }
        return 0 + "";
    }
}