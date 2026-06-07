package com.facade.subsystem;

import com.facade.model.SubsystemAction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Subsystem — manages HVAC, fans, and air quality.
 */
@Component
public class ClimateSystem {

    public List<SubsystemAction> setTemperature(int celsius) {
        return List.of(new SubsystemAction("ClimateSystem",
                "Target temperature set to " + celsius + "°C", "🌡️"));
    }

    public List<SubsystemAction> setFanSpeed(String speed) {
        return List.of(new SubsystemAction("ClimateSystem",
                "Fan speed → " + speed, "💨"));
    }

    public List<SubsystemAction> enableEcoMode() {
        return List.of(new SubsystemAction("ClimateSystem",
                "Eco mode ENABLED — optimising energy use", "🌿"));
    }

    public List<SubsystemAction> turnOff() {
        return List.of(new SubsystemAction("ClimateSystem",
                "HVAC system OFF", "❄️"));
    }

    public List<SubsystemAction> setHumidity(int percent) {
        return List.of(new SubsystemAction("ClimateSystem",
                "Humidity target set to " + percent + "%", "💧"));
    }

    public List<SubsystemAction> enableNightMode() {
        return List.of(new SubsystemAction("ClimateSystem",
                "Night mode: silent fan, 19°C", "🌙"));
    }
}
