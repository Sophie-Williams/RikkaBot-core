package com.rikkabot.rikkabotcore.bot.middlewares;


import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;

/**
 * Encryption middleware.
 * ======================
 *
 * Serves as middleware between RikkaBot-core and TyrantsEye
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class EncryptionMiddleware {
    public static final BigInteger ENC_BASE_GENERATOR = new BigInteger(DatatypeConverter.parseHexBinary("9d199fb7785e1616f60c8f717ca46f7c088bdb8986095380499fdfa93a1268020a3164427f5c27c2b9df41676063a43e140f57ae2acd207fdf5f48d44b4bca1a"));
    public static final BigInteger ENC_PRIME_MODULUS = new BigInteger(DatatypeConverter.parseHexBinary("d1bca8f2c1c71815111fe07923018747084ab8d546ea5d3a06671a814726898380acfd81a3843221ce68939ce90c8d665e762398ac86058b5860bf4623ce9417"));
    /**
     * Encryption key generated by the client
     */
    public static BigInteger privateKey;
    /**
     * Encrypts the output bytes.
     *
     * @param out Output bytes to encrypt.
     *
     * @return Encrypted byte array.
     */
    public static byte[] encrypt(byte[] out) {
        return out;
    }

    /**
     * Decrypts the input bytes.
     *
     * @param in Input bytes to decrypt.
     *
     * @return Decrypted byte array.
     */
    public static byte[] decrypt(byte[] in) {
        return in;
    }
}
