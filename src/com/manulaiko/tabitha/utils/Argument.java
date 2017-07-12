package com.manulaiko.tabitha.utils;

/**
 * Argument handler.
 * =================
 *
 * Base class for all argument handlers.
 *
 * The method `handle` is executed when an argument can be handled by the instance.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public abstract class Argument
{
    /**
     * Argument name.
     */
    private String _name = "";

    /**
     * Argument value.
     */
    private String _value = "";

    /**
     * Sets argument name.
     *
     * @param name New argument name.
     */
    public void name(String name)
    {
        this._name = name;
    }

    /**
     * Returns argument name.
     *
     * @return Argument name.
     */
    public String name()
    {
        return this._name;
    }

    /**
     * Sets argument value.
     *
     * @param value New argument value.
     */
    public void value(String value)
    {
        this._value = value;
    }

    /**
     * Returns argument value.
     *
     * @return Argument value.
     */
    public String value()
    {
        return this._value;
    }

    /**
     * Checks that the argument can be handled
     * by this instance.
     *
     * @param arg Argument.
     *
     * @return `true` if the argument can be handled by this instance, `false` if not.
     */
    public abstract boolean canHandle(String arg);

    /**
     * Handles the argument.
     */
    public abstract void handle();
}
