import java.math.BigInteger;
import java.time.LocalDateTime;

public class Attack {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("121999492637070040497464880653482451122159715698431661862504934268987469885677710797799523307422120568454593141727668682332216679465216347609718241998150443969871262326615939878834844507147192404574401325870276945218845272041195113380201145626974399759092850500988371156171063899568397919181947787377580179491");
        BigInteger q = new BigInteger("121999492637070040497464880653482451122159715698431661862504934268987469885677710797799523307422120568454593141727668682332216679465216347609718241998150443969871262326615939878834844507147192404574401325870276945218845272041195113380201145626974399759092850500988371156171063899568397919181947787377580179803");

        String plainText = "Wojskowa Akademia Techniczna";
        BigInteger plainNumber = new BigInteger(plainText.getBytes());

        System.out.println("Zlozonosc faktoryzacji w przypadku dobrego doboru p i q: 2^" + p.bitLength() * 2);
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
