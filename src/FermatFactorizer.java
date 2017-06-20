import java.math.BigInteger;

public class FermatFactorizer {

    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

    public static BigInteger factorize(BigInteger input) {
        BigInteger a = sqrt(input);
        BigInteger b = a.multiply(a).subtract(input);
        BigInteger bsqrt = sqrt(b);
        BigInteger aMinusB = a.subtract(bsqrt);

        BigInteger c = new BigInteger("40");
        BigInteger aMinusBpseudoCeil = a.subtract(bsqrt).add(c);
        BigInteger result;

        while (!sqrt(bsqrt).pow(2).equals(bsqrt) && aMinusBpseudoCeil.subtract(aMinusB).compareTo(c) > -1) {
            a = a.add(BigInteger.ONE);
            b = a.multiply(a).subtract(input);

            bsqrt = sqrt(b);
            aMinusBpseudoCeil = aMinusB;
            aMinusB = a.subtract(bsqrt);
        }

        if (sqrt(b).pow(2).equals(b)) {
            result = aMinusB;
        }

        else {
            boolean solved = false;
            BigInteger p = aMinusB.add(TWO);

            if (p.remainder(TWO).intValue() == 0) {
                p = p.add(BigInteger.ONE);
            }
            while (!solved) {
                p = p.subtract(TWO);
                if (input.remainder(p).equals(BigInteger.ZERO)) {
                    solved = true;
                }
            }

            result = p;
        }

        return result;
    }


    private static BigInteger sqrt(BigInteger n) {
        BigInteger n1 = BigInteger.ZERO;
        BigInteger n2 = n1.setBit(2 * n.bitLength());
        BigInteger n3;

        do {
            n3 = n1.add(n2);
            if (n3.compareTo(n) != 1) {
                n = n.subtract(n3);
                n1 = n3.add(n2);
            }
            n1 = n1.shiftRight(1);
            n2 = n2.shiftRight(2);
        } while (n2.bitCount() != 0);

        return n1;
    }
}
