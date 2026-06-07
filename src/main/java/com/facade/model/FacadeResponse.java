package com.facade.model;

import java.util.List;

/**
 * API response — includes the activated scene name, a human-readable
 * summary, and the full list of subsystem actions the Facade triggered.
 */
public class FacadeResponse {

    private String scene;
    private String summary;
    private List<SubsystemAction> actions;

    public FacadeResponse(String scene, String summary, List<SubsystemAction> actions) {
        this.scene   = scene;
        this.summary = summary;
        this.actions = actions;
    }

    public String getScene()                    { return scene; }
    public String getSummary()                  { return summary; }
    public List<SubsystemAction> getActions()   { return actions; }
}
