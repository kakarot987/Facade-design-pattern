package com.facade.subsystem;

import com.facade.model.SubsystemAction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsystem — controls all lighting in the home.
 * Has many fine-grained methods; clients should NOT call these directly.
 * The Facade selects and sequences the right calls per scene.
 */
@Component
public class LightingSystem {

    public List<SubsystemAction> turnOff() {
        return List.of(new SubsystemAction("LightingSystem", "All lights OFF", "💡"));
    }

    public List<SubsystemAction> setBrightness(int percent) {
        return List.of(new SubsystemAction("LightingSystem",
                "Brightness set to " + percent + "%", "💡"));
    }

    public List<SubsystemAction> setWarmWhite() {
        return List.of(new SubsystemAction("LightingSystem",
                "Color temperature → warm white (2700K)", "🟡"));
    }

    public List<SubsystemAction> setCoolWhite() {
        return List.of(new SubsystemAction("LightingSystem",
                "Color temperature → cool white (6500K)", "⚪"));
    }

    public List<SubsystemAction> setScene(String scene) {
        return List.of(new SubsystemAction("LightingSystem",
                "Scene preset applied: " + scene, "🎨"));
    }

    public List<SubsystemAction> enableMotionSensors() {
        return List.of(new SubsystemAction("LightingSystem",
                "Motion-activated lighting ENABLED", "👁️"));
    }

    public List<SubsystemAction> externalLightsOn() {
        return List.of(new SubsystemAction("LightingSystem",
                "Exterior lights turned ON", "🏠"));
    }
}
