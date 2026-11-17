package com.facade.controller;

import com.facade.facade.HomeAutomationFacade;
import com.facade.model.FacadeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * HomeController — thin REST layer.
 *
 * Notice how lean this is: no subsystem knowledge, no ordering logic,
 * no conditionals across systems. It just delegates to the Facade.
 *
 * That IS the point of the Facade Pattern.
 *
 * Endpoints:
 *   POST /api/home/scene/{name}   — Activate a named scene
 *   GET  /api/home/scenes         — List available scenes
 */
@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*")
public class HomeController {

    private final HomeAutomationFacade facade;

    public HomeController(HomeAutomationFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/scene/{name}")
    public ResponseEntity<FacadeResponse> activateScene(@PathVariable String name) {
        FacadeResponse response = switch (name.trim().toLowerCase(java.util.Locale.ROOT)) {
            case "movie"   -> facade.activateMovieMode();
            case "morning" -> facade.activateMorningMode();
            case "leave"   -> facade.activateLeaveMode();
            case "sleep"   -> facade.activateSleepMode();
            case "party"   -> facade.activatePartyMode();
            default        -> throw new IllegalArgumentException("Unknown scene: " + name);
        };
        return ResponseEntity.ok(response);
    }

    @GetMapping("/scenes")
    public ResponseEntity<List<Map<String, String>>> listScenes() {
        return ResponseEntity.ok(List.of(
            Map.of("id", "movie",   "label", "Movie Night",   "icon", "🎬", "desc", "Dim lights, close blinds, surround sound"),
            Map.of("id", "morning", "label", "Good Morning",  "icon", "☀️",  "desc", "Bright lights, open blinds, jazz music"),
            Map.of("id", "leave",   "label", "Leaving Home",  "icon", "🚗", "desc", "Everything off, doors locked, alarm on"),
            Map.of("id", "sleep",   "label", "Good Night",    "icon", "🌙", "desc", "Lights off, night climate, alarm armed"),
            Map.of("id", "party",   "label", "Party Mode",    "icon", "🎉", "desc", "Colour lights, loud music, door unlocked")
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadScene(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
}
