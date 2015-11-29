public class multiplicationTable {

    public static void calcMultTable(int start, int ende){

        for (int i = start ; i <= ende; i++) {

            for (int j = start; j <= ende; j++) {
                System.out.printf("%4d",i*j);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        int start = 1;
        int ende = 32;

        calcMultTable(start, ende);

    }
}
