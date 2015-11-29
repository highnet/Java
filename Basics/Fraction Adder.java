public class Fraction {

    private int numerator;   // (Zähler)
    private int denominator; // (Nenner); denominator > 0 is required

    // New fraction with numerator num and denominator denom; denom != 0 is required.
    public Fraction(int num, int denom) {

        if (denom < 0) { // denominator > 0 is required, but value of fraction shall be as specified by num and denom
            num = (num);
            denom = (1);
        }
        this.numerator = (num);
        this.denominator = (denom);
        System.out.println(this.numerator + "/" + this.denominator);

       this.reduce();
    }

    // Returns a new fraction with the result of adding frac to this fraction.
    // If frac is null, the result shall contain a copy of this fraction (new object of same state).
    public Fraction addFrac(Fraction frac){
        if(frac == null){
            return (new Fraction(this.numerator,this.denominator));
        }
        return (new Fraction(((this.numerator*frac.denominator) + (frac.numerator * this.denominator)),(this.denominator * frac.denominator)));
    }

    // Reduce the fraction. Use 0/1 for 0.
    private void reduce() {
        if (numerator == 0) {
            denominator = (0);
        } else {

            int gcd = gcd((numerator < 0) ? (-numerator) : numerator, denominator);
            System.out.println("gcd= " + gcd(this.numerator,this.denominator));
        

            numerator = (numerator / gcd);
            denominator = (denominator / gcd);
            System.out.println("reduced... to " + numerator + "/" + denominator);
            System.out.println("----");

        }
    }

    // Returns the greatest common divisor of a und b.
    private int gcd(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return (r+b);
    }

    // Adds two fractions, one specified by numerator num1 and denominator denom1, the other by numerator num1 and
    // denominator denom2. The resulting array contains the numerator at index 0 and the denominator at index 1.
    // denom1 != 0 and denom2 != 0 is required.
    public static int[] addFractions(int num1, int denom1, int num2, int denom2) {
        return ( denom1 == 0 ?  null: denom2 == 0 ? null: new int[]{((num1 * denom2) + (num2 * denom1)),denom1*denom2});
    }

    
    // Just for testing ...
    public static void main(String[] args) {
    	boolean flag = false;
    	System.out.println("Fraction A");
        Fraction fractionX = new Fraction(2,1);
        
        if (fractionX.numerator == 0){
        	System.out.println(fractionX.numerator);
        }
        else if (fractionX.denominator != 0 || fractionX.denominator != 1){
        System.out.println(fractionX.numerator + "/" +fractionX.denominator);
        }
        
        else{
        	System.out.println(fractionX.numerator);
        }
        System.out.println("----");
    	System.out.println("Fraction B");

        Fraction fractionA = new Fraction(19,22);
    	System.out.println("Fraction C");

        Fraction fractionB = new Fraction(235,100);

        System.out.print("Fraction B + Fraction C = ");
        fractionA.addFrac(fractionB); // Add two fractions.
        
        if (fractionA.denominator != 0 ){
            System.out.print(fractionA.numerator + "/" +fractionA.denominator + " \u2245 " );
            System.out.println((double) fractionA.numerator / fractionB.denominator);
            }
        
        else if (fractionA.numerator == 0){
        	System.out.println(fractionA.numerator);

        }
            else{
            	System.out.println(fractionA.numerator);
            }
        
        int num1 = 1;
        int denom1 = 2;
        
        int num2 = 2;
        int denom2 = 3;
        
        
        int[] result = addFractions(num1,denom1,num2,denom2);
        System.out.print(num1 + "/" + denom1 + " + " + num2 + "/" + denom2 + " = ");
        
        System.out.println(result[0] + "/" + result[1]);
        
    }

}
