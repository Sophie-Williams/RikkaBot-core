package com.manulaiko.tabitha.configuration.listener;

/**
 * Event Listener interface.
 * =========================
 *
 * Interface for the event listeners.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IEventListener
{
    /**
     * Fires a specified event.
     *
     * @param event Event to fire.
     */
    void fire(Class<? extends IEvent> event);
}
