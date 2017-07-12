package com.manulaiko.tabitha.configuration.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Event manager.
 * ==============
 *
 * Manages all event listeners.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class EventManager
{
    /**
     * Registered event listeners.
     */
    private Map<Class<? extends IEvent>, List<IEventListener>> _listeners = new HashMap<>();

    /**
     * Adds an event to its listener.
     *
     * @param event    Event to listen.
     * @param listener Event listener.
     */
    public void listen(Class<? extends IEvent> event, IEventListener listener)
    {
        if(!this.listeners().containsKey(event)) {
            this.listeners().put(event, new ArrayList<>());
        }

        this.listeners().get(event).add(listener);
    }

    /**
     * Fires an event.
     *
     * @param event Event to fire.
     */
    public void fire(Class<? extends IEvent> event)
    {
        if(!this.listeners().containsKey(event)) {
            return;
        }

        this.listeners().get(event).forEach((e)->{
            e.fire(event);
        });
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns registered event listeners.
     *
     * @return Registered event listeners.
     */
    public Map<Class<? extends IEvent>, List<IEventListener>> listeners()
    {
        return this._listeners;
    }

    /**
     * Sets registered event listeners.
     *
     * @param listeners New event listeners.
     */
    public void listeners(Map<Class<? extends IEvent>, List<IEventListener>> listeners)
    {
        this._listeners = listeners;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
