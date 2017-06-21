import java.math.BigInteger;

class FermatFactorizer {

    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

    static BigInteger factorize(BigInteger input) {
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
        } else {
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
        BigInteger a = BigInteger.ONE;
        BigInteger b = n.shiftRight(5).add(BigInteger.valueOf(8));
        while (b.compareTo(a) >= 0) {
            BigInteger mid = a.add(b).shiftRight(1);
            if (mid.multiply(mid).compareTo(n) > 0) {
                b = mid.subtract(BigInteger.ONE);
            } else {
                a = mid.add(BigInteger.ONE);
            }
        }
        return a.subtract(BigInteger.ONE);
    }

}
