package com.rikkabot.rikkabotcore.bot.handlers;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.bot.GameConnection;
import com.rikkabot.rikkabotcore.bot.commands.Command;
import com.rikkabot.rikkabotcore.bot.commands.incoming.HandshakeCommand;

/**
 * Created by piotr on 15.07.17.
 */
public class HandshakeCommandHandler extends Handler<HandshakeCommand> {

    public HandshakeCommandHandler(GameConnection connection, Command command) {
        super(connection, command);
    }

    @Override
    public void handle() {
        Console.debug("received hch");
    }
}
