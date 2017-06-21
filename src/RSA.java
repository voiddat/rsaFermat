import java.math.BigInteger;

public class RSA {
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger p;
    private BigInteger q;

    RSA(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;

        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        n = p.multiply(q);

        publicKey = BigInteger.valueOf(65521L); // liczba pierwsza mniejsza od fi

        privateKey = publicKey.modInverse(phi); // d = (publicKey^-1) mod(fi)
    }

    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, n);
    }

    BigInteger decrypt(BigInteger cipherText) {
        return cipherText.modPow(privateKey, n);
    }

    BigInteger getPrivateKey() {
        return privateKey;
    }

    BigInteger getPublicKey() {
        return publicKey;
    }

    BigInteger getN() {
        return n;
    }

    BigInteger getP() {
        return p;
    }

    BigInteger getQ() {
        return q;
    }

}