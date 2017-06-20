import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private static final SecureRandom secureRandom = new SecureRandom();

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

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getPhi() {
        return phi;
    }

    public void setPhi(BigInteger phi) {
        this.phi = phi;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }
}