package com.facade.subsystem;

import com.facade.model.SubsystemAction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Subsystem — manages locks, alarms, and cameras.
 */
@Component
public class SecuritySystem {

    public List<SubsystemAction> arm() {
        return List.of(new SubsystemAction("SecuritySystem",
                "Alarm system ARMED", "🔒"));
    }

    public List<SubsystemAction> disarm() {
        return List.of(new SubsystemAction("SecuritySystem",
                "Alarm system DISARMED", "🔓"));
    }

    public List<SubsystemAction> lockAllDoors() {
        return List.of(new SubsystemAction("SecuritySystem",
                "All doors LOCKED", "🚪"));
    }

    public List<SubsystemAction> unlockFrontDoor() {
        return List.of(new SubsystemAction("SecuritySystem",
                "Front door UNLOCKED", "🚪"));
    }

    public List<SubsystemAction> enableCameras() {
        return List.of(new SubsystemAction("SecuritySystem",
                "All cameras ACTIVE — recording", "📷"));
    }

    public List<SubsystemAction> enablePerimeterAlerts() {
        return List.of(new SubsystemAction("SecuritySystem",
                "Perimeter motion alerts ENABLED", "🚨"));
    }

    public List<SubsystemAction> disableCameras() {
        return List.of(new SubsystemAction("SecuritySystem",
                "Indoor cameras PAUSED (privacy mode)", "🙈"));
    }
}
