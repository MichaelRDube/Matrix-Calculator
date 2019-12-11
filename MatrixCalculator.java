import java.util.Scanner;
public class MatrixCalculator {
    public static class Fraction {
        int numerator;
        int denominator;
        public Fraction(int a, int b) {
            numerator = a;
            denominator = b;
            this.simplify();
        }
        
        public Fraction(int a) {
            numerator = a;
            denominator = 1;
        }
        
        public Fraction (Fraction a) {
            numerator = a.numerator;
            denominator = a.denominator;
            this.simplify();
        }
        
        public Fraction simplify() {
            if(denominator == 1 || denominator == -1) {
                restoreSign();
                return this;
            }
            int max = 0;
            if(numerator == 0) {
                denominator = 1;
                return this;
            }
            else if (Math.abs(numerator) > Math.abs(denominator)) {
                max = Math.abs(numerator);
            }
            else if(Math.abs(numerator) < Math.abs(denominator)) {
                max = Math.abs(denominator);
            }
            else {
                numerator /= Math.abs(numerator);
                denominator /= Math.abs(denominator);
                restoreSign();
                return this;
            }
            for (int i = 2; i <= max/2; i++) {
                while (numerator%i == 0 && denominator%i == 0) {
                    numerator /= i;
                    denominator /= i;
                }
            }
            restoreSign();
            return this;
        }
        
        public void restoreSign() {
            if(denominator < 0) {
                denominator *= -1;
                numerator *= -1;
            }
        }
        
        public void reciprocal() {
            if(numerator == 0) {
                return;
            }
            int holder = numerator;
            numerator = denominator;
            denominator = holder;
        }
        
        public static Fraction reciprocal(Fraction a) {
            if(a.numerator == 0) {
                return a;
            }
            Fraction c = new Fraction(a.denominator, a.numerator);
            return c;
        }
        
        public void print() {
            if(denominator == 1) {
                System.out.print(numerator);
            }
            else if(numerator == 0) {
                System.out.print(numerator);
            }
            else {
                System.out.print(numerator + "/" + denominator);
            }
        }
        
        public String printFracString() {
            String fraction = "";
            if(denominator == 1) {
                fraction += numerator;
            }
            else if(numerator == 0) {
                fraction += numerator;
            }
            else {
                fraction += numerator + "/" + denominator;
            }
            return fraction;
        }
        
        public static Fraction add(Fraction a, Fraction b) {
            int newNumerator = (a.numerator * b.denominator) + (b.numerator * a.denominator);
            int newDenominator = a.denominator * b.denominator;
            Fraction c = new Fraction(newNumerator, newDenominator);
            c.simplify();
            return c;
        }
        
        public static Fraction add(Fraction a, int b) {
            Fraction c = new Fraction((a.denominator*b) + a.numerator, a.denominator);
            c.simplify();
            return c;
        }
        
        public static Fraction add(int b, Fraction a) {
            Fraction c = new Fraction((a.denominator*b) + a.numerator, a.denominator);
            c.simplify();
            return c;
        }

        public static Fraction subtract(Fraction a, Fraction b) {
            return add(a, Fraction.multiply(b, -1));
        }
        
        public static Fraction subtract(Fraction a, int b) {
            Fraction c = new Fraction(Fraction.add(a, b*-1));
            return c;
        }
        
        public static Fraction subtract(int b, Fraction a) {
            Fraction c = new Fraction(Fraction.add(b, Fraction.multiply(a, -1)));
            return c;
        }

        public static Fraction multiply(Fraction a, Fraction b) {
            a.simplify();
            b.simplify();
            Fraction c = new Fraction(a.numerator*b.numerator, a.denominator*b.denominator);
            c.simplify();
            return c;
        }

        public static Fraction multiply(Fraction a, int b) {
            a.restoreSign();
            Fraction c = new Fraction(a.numerator * b);
            return c;
        }

        public static Fraction multiply(int a, Fraction b) {
            b.restoreSign();
            Fraction c = new Fraction(b.numerator * a);
            return c;
        }

        public static Fraction divide(Fraction a, Fraction b) {
            Fraction c = new Fraction(b.denominator, b.numerator);
            c.restoreSign();
            return multiply(a, c);
        }

        public static Fraction divide(Fraction a, int b) {
            Fraction c = new Fraction(a.numerator, a.denominator * b);
            c.restoreSign();
            return c;
        }

        public static Fraction divide(int a, Fraction b) {
            Fraction c = new Fraction(b.denominator, b.numerator);
            c.numerator = a*c.numerator;
            c.restoreSign();
            return b;
        }
    }
    
    public static class Matrix {
        int n;
        int m;
        Fraction[][] mat;
        public Matrix() {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter number of rows: ");
            //safeguard against strings
            n = in.nextInt();
            System.out.print("Enter number of columns: ");
            m = in.nextInt();
            
            mat = new Fraction[n][m];
            
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    System.out.println("Entry for row " + (i+1) + ", column " + (j+1) + ": ");
                    mat[i][j] = new Fraction(in.nextInt());
                }
            }
            
            System.out.println("Matrix created:");
            printMatrix();
        }
        
        public Matrix(int n, int m) {
            this.n = n;
            this.m = m;
            mat = new Fraction[n][m];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    mat[i][j] = new Fraction(0);
                }
            }
        }
        
        public Matrix(Matrix entry) {
            if (entry != null) {
                n = entry.n;
                m = entry.m;

                mat = entry.mat;
            }
        }
        
        
        public void printMatrix() {
            if (this != null) {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        mat[i][j].print();
                        System.out.print("      ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            else {
                System.out.println("Matrix is null");
            }
        }
        
        public static void printMatrix(Matrix a) {
            if (a != null) {
                for(int i = 0; i < a.n; i++) {
                    for(int j = 0; j < a.m; j++) {
                        a.mat[i][j].print();
                        System.out.print("      ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            else {
                System.out.println("Matrix is null");
            }
        }
        
        public static String printMatrixFormatted(Matrix a) {
            String matrix = "";
            if (a != null) {
                for(int i = 0; i < a.n; i++) {
                    for(int j = 0; j < a.m; j++) {
                        matrix += a.mat[i][j].printFracString();
                    }
                    System.out.println();
                }
                System.out.println();
            }
            else {
                System.out.println("Matrix is null");
            }
            
            return matrix;
        }
        
        public static Matrix matrixMult(Matrix a, Matrix b) {
            if(a.m != b.n) {
                return null;
            }
            Matrix c = new Matrix(a.n, b.m);
            for(int i = 0; i < a.n; i++) {
                for(int j = 0; j < b.m; j++) {
                    c.mat[i][j] = new Fraction(0, 1);
                    for(int k = 0; k < a.m; k++) {
                        c.mat[i][j] = Fraction.add(c.mat[i][j], Fraction.multiply(a.mat[i][k],b.mat[k][j]));
                    }   
                }
            }
            return c;
        }

        public static Matrix scalarMult(Fraction a, Matrix b) {
            Matrix c = new Matrix(b);
            for(int i = 0; i < b.n; i++) {
                for(int j = 0; j < b.m; j++) {
                    c.mat[i][j] = Fraction.multiply(a, c.mat[i][j]);
                }
            }
            return c;
        }

        public static Matrix scalarMult(Matrix b, Fraction a) {
            Matrix c = new Matrix(b);
            for(int i = 0; i < b.n; i++) {
                for(int j = 0; j < b.m; j++) {
                    c.mat[i][j] = Fraction.multiply(a, c.mat[i][j]);
                }
            }
            return c;
        }
        
        public static Matrix matrixAdd(Matrix a, Matrix b) {
            if(a.n != b.n || a.m != b.m) {
                return null;
            }
            Matrix c = new Matrix(a.n, a.m);
            for(int i = 0; i < a.n; i++) {
                for(int j = 0; j < a.m; j++) {
                    c.mat[i][j] = Fraction.add(a.mat[i][j], b.mat[i][j]);
                }
            }
            return c;
        }
        
        public static Matrix matrixSubtract(Matrix a, Matrix b) {
            return matrixAdd(a, scalarMult(b, new Fraction(-1)));
        }
        
        public void rowAdd(int to, int from) {
            for(int i = 0; i < m; i++) {
                mat[to][i] = Fraction.add(mat[to][i], mat[from][i]);
            }
        }
        
        public void rowAdd(int to, int from, Fraction multiplier) {
            for(int i = 0; i < m; i++) {
                mat[to][i] = Fraction.add(mat[to][i], Fraction.multiply(mat[from][i], multiplier));
            }
        }
        
        public void rowMult(int target, Fraction multiplier) {
            for(int i = 0; i < m; i++) {
                mat[target][i] = Fraction.multiply(multiplier, mat[target][i]);
            }
        }
        
        public void rowSwitch(int firstRow, int secondRow) {
            Fraction holder = new Fraction(0);
            for(int i = 0; i < m; i++) {
                holder = mat[firstRow][i];
                mat[firstRow][i] = mat[secondRow][i];
                mat[secondRow][i] = holder;
            }
        }
        
        public static Matrix identity(int n) {
            Matrix identity = new Matrix(n, n);
            for(int i = 0; i < n; i++) {
                identity.mat[i][i].numerator = 1;
            }
            return identity;
        }
        
        public static Matrix appendMatrix(Matrix a, Matrix b) {
            if(a.n != b.n) {
                return null;
            }
            Matrix c = new Matrix(a.n, a.m + b.m);
            for(int i = 0; i < a.n; i++) {
                for(int j = 0; j < a.m; j++) {
                    c.mat[i][j] = a.mat[i][j];
                }
            }
            for(int i = 0; i < b.n; i++) {
                for(int j = 0; j < b.m; j++) {
                    c.mat[i][a.m + j] = b.mat[i][j];
                }
            }
            return c;
        }
        
        public static Matrix RREFSteps(Matrix c) {
            Matrix a = new Matrix(c);
            Fraction multiplier = new Fraction(1);
            int i = 0;
            int j = 1;
            for(i = 0; i < a.n && i < a.m; i++) {
                j = i+1;
                while(a.mat[i][i].numerator == 0 && j < a.n) {
                    a.rowSwitch(i, j);
                    System.out.println(" R" + (i+1) + " <-> R" + (j+1));
                    j++;
                    a.printMatrix();
                }
                for(j = i+1; j < a.n; j++) {
                    if (a.mat[i][i].numerator != 0) {
                        multiplier = Fraction.divide(a.mat[j][i], a.mat[i][i]);
                        multiplier.numerator *= -1;
                        multiplier.restoreSign();
                        a.rowAdd(j, i, multiplier);
                        multiplier.print();
                        System.out.println(" R" + (i+1) + " -> R" + (j+1));
                        a.printMatrix();
                    }
                    
                }
            }
            i--;
            a = sortLeadingOnes(a);
            int min;
            if(a.m < a.n) {
                min = a.m;
            }
            else {
                min = a.n;
            }
            for(j = min-1; j >= 0; j--) {
                multiplier.numerator = a.mat[j][j].denominator;
                multiplier.denominator = a.mat[j][j].numerator;
                multiplier.restoreSign();
                if(multiplier.denominator != 0) {
                    a.rowMult(j, multiplier);
                    multiplier.print();
                    System.out.println(" R" + (j+1));
                }
                a.printMatrix();
                for(i = j-1; i >= 0; i--) {
                    if(a.mat[j][j].numerator != 0) {
                        multiplier = Fraction.divide(a.mat[i][j], a.mat[j][j]);
                        multiplier.numerator *= -1;
                        multiplier.restoreSign();
                        a.rowAdd(i, j, multiplier);
                        multiplier.print();
                        System.out.println(" R" + (j+1) + " -> R" + (i+1));
                        a.printMatrix();
                    }
                }
            }
            a = sortLeadingOnes(a);
            return a;
        }
        
        public static Matrix inverseSteps(Matrix b) {
            Matrix a = new Matrix(b);
            if(a.n != a.m) {
                System.out.println("Matrix not invertible (not square).");
                return null;
            }
            if(Matrix.determinant(a).numerator == 0) {
                System.out.println("Matrix not invertible (Determinant = 0)");
                return null;
            }
            a = Matrix.appendMatrix(a, Matrix.identity(a.n));
            a = Matrix.RREFSteps(a);
            Matrix c = new Matrix(a.n, a.n);
            
            for(int i = 0; i < a.n; i++) {
                for(int j = 0; j < a.n; j++) {
                    c.mat[i][j] = a.mat[i][a.n + j];
                }
            }
            return c;
        }
        
        public static Matrix inverse(Matrix b) {
            Matrix a = new Matrix(b);
            if(a.n != a.m) {
                System.out.println("Matrix not invertible (not square).");
                return null;
            }
            if(Matrix.determinant(a).numerator == 0) {
                System.out.println("Matrix not invertible (Determinant = 0)");
                return null;
            }
            a = Matrix.appendMatrix(a, Matrix.identity(a.n));
            a = Matrix.RREF(a);
            Matrix c = new Matrix(a.n, a.n);
            
            for(int i = 0; i < a.n; i++) {
                for(int j = 0; j < a.n; j++) {
                    c.mat[i][j] = a.mat[i][a.n + j];
                }
            }
            return c;
        }
        
        public static Matrix RREF(Matrix c) {
            Matrix a = new Matrix(c);
            Fraction multiplier = new Fraction(1);
            int i = 0;
            int j = 1;
            for(i = 0; i < a.n && i < a.m; i++) {
                j = i+1;
                while(a.mat[i][i].numerator == 0 && j < a.n) {
                    a.rowSwitch(i, j);
                    j++;
                }
                for(j = i+1; j < a.n; j++) {
                    if (a.mat[i][i].numerator != 0) {
                        multiplier = Fraction.divide(a.mat[j][i], a.mat[i][i]);
                        multiplier.numerator *= -1;
                        multiplier.restoreSign();
                        a.rowAdd(j, i, multiplier);
                    }
                    
                }
            }
            i--;
            a = sortLeadingOnes(a);
            int min;
            if(a.m < a.n) {
                min = a.m;
            }
            else {
                min = a.n;
            }
            for(j = min-1; j >= 0; j--) {
                multiplier.numerator = a.mat[j][j].denominator;
                multiplier.denominator = a.mat[j][j].numerator;
                multiplier.restoreSign();
                if(multiplier.denominator != 0) {
                    a.rowMult(j, multiplier);
                }
                for(i = j-1; i >= 0; i--) {
                    if(a.mat[j][j].numerator != 0) {
                        multiplier = Fraction.divide(a.mat[i][j], a.mat[j][j]);
                        multiplier.numerator *= -1;
                        multiplier.restoreSign();
                        a.rowAdd(i, j, multiplier);
                    }
                }
            }
            a = sortLeadingOnes(a);
            return a;
        }
    
        public static Matrix sortLeadingOnes(Matrix a) {
            Matrix c = new Matrix(a);
            boolean sorted = false;
            for(int i = 0; i < a.n; i++) {
                for(int j = i +1; j < a.n; j++) {
                    if(leadingOneLocation(c, j) < leadingOneLocation(c, i)) {
                        c.rowSwitch(j, i);
                    }
                }
            }

            return c;
        }

        public static int leadingOneLocation(Matrix a, int rowNum) {
            for (int i = 0; i < a.m; i++) {
                    if(a.mat[rowNum][i].numerator != 0) {
                        return i;
                    }
            }
            return a.m;
        }

        public static Matrix detSlice(Matrix a, int colNum) {
            Matrix c = new Matrix(a.n-1, a.n-1);

            for(int i = 0; i < c.n; i++) {
                for(int j = 0; j < c.n; j++) {
                    if(j < colNum) {
                        c.mat[i][j] = a.mat[i+1][j];
                    }
                    else {
                        c.mat[i][j] = a.mat[i+1][j+1];
                    }
                }
            }

            return c;
        }

        public static Fraction determinant(Matrix a) {
            if(a.n == 1) {
                return a.mat[0][0];
            }
            Fraction sum = new Fraction(0);
            Fraction currentMultiplier = new Fraction(0);
            for(int i = 0; i < a.n; i++) {
                currentMultiplier = a.mat[0][i];
                if(i%2 == 1) {
                    currentMultiplier = Fraction.multiply(currentMultiplier, -1);
                }
                sum = Fraction.add(sum, Fraction.multiply(currentMultiplier, determinant(detSlice(a, i))));
            }

            return sum;
        }

        public static Matrix transpose(Matrix a) {
            Matrix c = new Matrix(a.m, a.n);
            for(int i = 0; i < a.n; i++) {
                for(int j = 0; j < a.m; j++) {
                    c.mat[j][i] = a.mat[i][j];
                }
            }
            
            return c;
        }
    }
    
    public static void main(String[] args) {
        Matrix a = new Matrix();
        //Matrix b = new Matrix(Matrix.inverseSteps(a));
        //Matrix.printMatrix(b);
        //a.rowSwitch(1, 3);
        //a = Matrix.inverse(a);
        //a = Matrix.detSlice(a, 1);
        //Matrix.determinant(a).print();
        //Matrix.matrixMult(a, b).printMatrix();
        a = Matrix.RREFSteps(a);
        //Matrix b = new Matrix();
        //a = Matrix.appendMatrix(a, b);
        //a = Matrix.transpose(a);
        //a.printMatrix();
        //Matrix c = new Matrix(matrixMult(a, b));
        
        //a = Matrix.scalarMult(a, four);
    }
    
}
