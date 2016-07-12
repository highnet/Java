    private static int recFactorial(int n) {

        if (n == 0){
            return 1;
        }

        return n * (recFactorial(n-1));
    }

    private static boolean isPrimeRec(int i) {
        return isPrimeRec(i, i - 1);
    }

    private static boolean isPrimeRec(int i, int j) {
        if (i == 1) {
            return false;
        } else if (j == 1) {
            return true;
        } else if (i % j == 0) {
            return false;
        } else return isPrimeRec(i, j - 1);

    }

    private static boolean isPrimeIter(int num) {

        if (num == 1) {
            return false;
        }

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
