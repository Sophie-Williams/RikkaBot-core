package com.rikkabot.rikkabotcore.bot.middlewares;


/**
 * Encryption middleware.
 * ======================
 *
 * Serves as middleware between RikkaBot-core and TyrantsEye
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class EncryptionMiddleware {
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
