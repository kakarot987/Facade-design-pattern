package com.facade.model;

/**
 * Represents a single low-level action taken by a subsystem.
 * Returned in the response so the frontend can visualize what
 * the Facade orchestrated behind the scenes.
 */
public class SubsystemAction {

    private String subsystem;   // e.g. "LightingSystem"
    private String action;      // e.g. "Set brightness to 20%"
    private String icon;        // emoji hint for the UI

    public SubsystemAction(String subsystem, String action, String icon) {
        this.subsystem = subsystem;
        this.action    = action;
        this.icon      = icon;
    }

    public String getSubsystem() { return subsystem; }
    public String getAction()    { return action; }
    public String getIcon()      { return icon; }
}
