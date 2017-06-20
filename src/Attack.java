import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Attack {
    public static void main(String[] args) {
        String plainText = "WAT";
        BigInteger plainNumber = new BigInteger(plainText.getBytes());

        BigInteger p = new BigInteger("65521327545435345427234324325326433254632432624432432544325432984903248320948320948324093284093284093248320948320948230948320507");
        BigInteger q = new BigInteger("65521327545435345427234324325326433254632432624432432544325432984903248320948320948324093284093284093248320948320948230948321029");

        System.out.println("Zlozonosc faktoryzacji w przypadku dobrego doboru p i q: 2^" + p.bitLength());
        RSA rsa = new RSA(p, q);

        System.out.println("p: " + rsa.getP());
        System.out.println("q: " + rsa.getQ());
        System.out.println("n: " + rsa.getN());
        System.out.println("Klucz publiczny: " + rsa.getPublicKey());
        System.out.println("Klucz prywatny: " + rsa.getPrivateKey());

        System.out.println("Wiadomość do zaszyfrowania: " + plainText);
        System.out.println("Wiadomość skonwertowana do BigIntegera: " + plainNumber);

        BigInteger cipherNumber = rsa.encrypt(plainNumber);

        System.out.println("Wiadomość zaszyfrowana: " + cipherNumber);

        BigInteger decrypted = rsa.decrypt(cipherNumber);

        System.out.println("Wiadomość odszyfrowana: " + new String(decrypted.toByteArray()));

        System.out.println("[" + LocalDateTime.now() + "] Faktoryzacja metoda Fermata rozpoczeta...");

        BigInteger factorFirst = FermatFactorizer.factorize(rsa.getN());
        BigInteger factorSecond = rsa.getN().divide(factorFirst);

        System.out.println("[" + LocalDateTime.now() + "] Faktoryzacja zakonczona");
        System.out.println("Czynniki: " + factorFirst + ", " + factorSecond);

        BigInteger phiFromAttack = (factorFirst.subtract(BigInteger.ONE)).multiply(factorSecond.subtract(BigInteger.ONE));

        BigInteger privateKeyFromAttack = rsa.getPublicKey().modInverse(phiFromAttack);

        decrypted = cipherNumber.modPow(privateKeyFromAttack, rsa.getN());
        System.out.println("Wiadomość odszyfrowana kluczem z ataku: " + new String(decrypted.toByteArray()));
    }
}
