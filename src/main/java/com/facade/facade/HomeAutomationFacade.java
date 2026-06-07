package com.facade.facade;

import com.facade.model.FacadeResponse;
import com.facade.model.SubsystemAction;
import com.facade.subsystem.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════╗
 * ║           HOME AUTOMATION FACADE                         ║
 * ║                                                          ║
 * ║  Provides a simple, unified interface over four complex  ║
 * ║  subsystems: Lighting, Security, Climate, Entertainment. ║
 * ║                                                          ║
 * ║  The client (Controller) calls ONE method per scene.     ║
 * ║  The Facade knows WHICH subsystem calls to make and      ║
 * ║  in WHAT ORDER — all that complexity is hidden here.     ║
 * ╚══════════════════════════════════════════════════════════╝
 *
 * Pattern Role: Facade
 *
 * Without Facade — client must know about 4 subsystems,
 *   their methods, their correct invocation order, and their
 *   inter-dependencies. Tight coupling everywhere.
 *
 * With Facade — client calls facade.activateMovieMode() and
 *   goes home early.
 */
@Service
public class HomeAutomationFacade {

    private final LightingSystem     lighting;
    private final SecuritySystem     security;
    private final ClimateSystem      climate;
    private final EntertainmentSystem entertainment;

    public HomeAutomationFacade(LightingSystem lighting,
                                 SecuritySystem security,
                                 ClimateSystem climate,
                                 EntertainmentSystem entertainment) {
        this.lighting      = lighting;
        this.security      = security;
        this.climate       = climate;
        this.entertainment = entertainment;
    }

    // ── Scene 1: Movie Night ─────────────────────────────────
    public FacadeResponse activateMovieMode() {
        List<SubsystemAction> actions = new ArrayList<>();

        actions.addAll(lighting.setBrightness(15));
        actions.addAll(lighting.setScene("cinema-amber"));
        actions.addAll(entertainment.closeBlinds());
        actions.addAll(entertainment.turnOnTV());
        actions.addAll(entertainment.enableSurroundSound());
        actions.addAll(entertainment.setVolume(65));
        actions.addAll(climate.setTemperature(22));
        actions.addAll(climate.setFanSpeed("low"));
        actions.addAll(security.disableCameras());

        return new FacadeResponse(
                "Movie Night",
                "Lights dimmed, blinds closed, surround sound active — enjoy the film.",
                actions
        );
    }

    // ── Scene 2: Good Morning ────────────────────────────────
    public FacadeResponse activateMorningMode() {
        List<SubsystemAction> actions = new ArrayList<>();

        actions.addAll(lighting.setBrightness(80));
        actions.addAll(lighting.setCoolWhite());
        actions.addAll(entertainment.openBlinds());
        actions.addAll(entertainment.playAmbientMusic("jazz"));
        actions.addAll(entertainment.setVolume(30));
        actions.addAll(climate.setTemperature(21));
        actions.addAll(climate.setFanSpeed("medium"));
        actions.addAll(security.disarm());

        return new FacadeResponse(
                "Good Morning",
                "Blinds open, cool light on, jazz playing — good morning.",
                actions
        );
    }

    // ── Scene 3: Leave Home ──────────────────────────────────
    public FacadeResponse activateLeaveMode() {
        List<SubsystemAction> actions = new ArrayList<>();

        actions.addAll(lighting.turnOff());
        actions.addAll(entertainment.turnOffTV());
        actions.addAll(entertainment.stopMusic());
        actions.addAll(entertainment.closeBlinds());
        actions.addAll(climate.enableEcoMode());
        actions.addAll(security.lockAllDoors());
        actions.addAll(security.arm());
        actions.addAll(security.enableCameras());
        actions.addAll(security.enablePerimeterAlerts());

        return new FacadeResponse(
                "Leaving Home",
                "Everything off, doors locked, alarm armed — you're good to go.",
                actions
        );
    }

    // ── Scene 4: Good Night ──────────────────────────────────
    public FacadeResponse activateSleepMode() {
        List<SubsystemAction> actions = new ArrayList<>();

        actions.addAll(lighting.turnOff());
        actions.addAll(lighting.enableMotionSensors());
        actions.addAll(entertainment.turnOffTV());
        actions.addAll(entertainment.stopMusic());
        actions.addAll(entertainment.closeBlinds());
        actions.addAll(climate.enableNightMode());
        actions.addAll(climate.setHumidity(50));
        actions.addAll(security.lockAllDoors());
        actions.addAll(security.arm());

        return new FacadeResponse(
                "Good Night",
                "Lights off, climate in night mode, doors locked — sleep well.",
                actions
        );
    }

    // ── Scene 5: Party Mode ──────────────────────────────────
    public FacadeResponse activatePartyMode() {
        List<SubsystemAction> actions = new ArrayList<>();

        actions.addAll(lighting.setScene("colour-cycle"));
        actions.addAll(lighting.setBrightness(70));
        actions.addAll(lighting.externalLightsOn());
        actions.addAll(entertainment.playAmbientMusic("electronic"));
        actions.addAll(entertainment.setVolume(80));
        actions.addAll(entertainment.openBlinds());
        actions.addAll(climate.setTemperature(20));
        actions.addAll(climate.setFanSpeed("high"));
        actions.addAll(security.disarm());
        actions.addAll(security.unlockFrontDoor());

        return new FacadeResponse(
                "Party Mode",
                "Lights cycling, music pumping, front door unlocked — let's go.",
                actions
        );
    }
}
