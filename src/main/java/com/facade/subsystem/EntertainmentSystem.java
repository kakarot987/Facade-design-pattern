package com.facade.subsystem;

import com.facade.model.SubsystemAction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Subsystem — manages TV, speakers, streaming, and blinds.
 */
@Component
public class EntertainmentSystem {

    public List<SubsystemAction> turnOnTV() {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "TV powered ON", "📺"));
    }

    public List<SubsystemAction> turnOffTV() {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "TV powered OFF", "📺"));
    }

    public List<SubsystemAction> setVolume(int level) {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "Volume set to " + level, "🔊"));
    }

    public List<SubsystemAction> closeBlinds() {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "Motorised blinds CLOSED", "🪟"));
    }

    public List<SubsystemAction> openBlinds() {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "Motorised blinds OPEN", "🪟"));
    }

    public List<SubsystemAction> playAmbientMusic(String genre) {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "Ambient " + genre + " playlist started", "🎵"));
    }

    public List<SubsystemAction> stopMusic() {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "Music STOPPED", "⏹️"));
    }

    public List<SubsystemAction> enableSurroundSound() {
        return List.of(new SubsystemAction("EntertainmentSystem",
                "Surround sound system ACTIVE", "🎧"));
    }
}
