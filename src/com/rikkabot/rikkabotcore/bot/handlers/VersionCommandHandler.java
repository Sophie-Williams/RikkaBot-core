package com.rikkabot.rikkabotcore.bot.handlers;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.bot.GameConnection;
import com.rikkabot.rikkabotcore.bot.commands.Command;
import com.rikkabot.rikkabotcore.bot.commands.incoming.VersionCommand;
import com.rikkabot.rikkabotcore.bot.commands.outgoing.HandshakeRequest;
import com.rikkabot.rikkabotcore.bot.middlewares.EncryptionMiddleware;

import java.math.BigInteger;
import java.util.Base64;

/**
 * Created by piotr on 15.07.17.
 */
public class VersionCommandHandler extends Handler<VersionCommand> {

    public VersionCommandHandler(GameConnection connection, Command command) {
        super(connection, command);
    }
    @Override
    public void handle() {
        if (command().compatible) {
            int _loc5_ = 0;
            String _loc6_ = null;
            String _loc1_ = new String();
            for (int _loc2_ = 0; _loc2_ < 128; _loc2_++) {
                _loc5_ = (int)Math.random() * 256;
                _loc6_ = String.valueOf(Integer.parseInt(String.valueOf(_loc5_), 16));
                if (_loc6_.length() == 1) {
                    _loc6_ = "0" + _loc6_;
                }
                _loc1_ += _loc6_;
            }
            EncryptionMiddleware.privateKey = new BigInteger(_loc1_, 16);
            BigInteger _loc3_ = EncryptionMiddleware.ENC_BASE_GENERATOR.modPow(EncryptionMiddleware.privateKey, EncryptionMiddleware.ENC_PRIME_MODULUS);

            HandshakeRequest handshakeRequest = new HandshakeRequest(_loc3_.toByteArray());
            connection().send(handshakeRequest);

            Console.debug("Sent handshake request with key: " + Base64.getEncoder().encodeToString(_loc3_.toByteArray()));
        }
    }
}
