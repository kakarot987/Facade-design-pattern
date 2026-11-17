package com.facade.facade;

import com.facade.subsystem.ClimateSystem;
import com.facade.subsystem.EntertainmentSystem;
import com.facade.subsystem.LightingSystem;
import com.facade.subsystem.SecuritySystem;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeAutomationFacadeTest {

    private final LightingSystem lighting = spy(new LightingSystem());
    private final SecuritySystem security = spy(new SecuritySystem());
    private final ClimateSystem climate = spy(new ClimateSystem());
    private final EntertainmentSystem entertainment = spy(new EntertainmentSystem());
    private final HomeAutomationFacade facade =
            new HomeAutomationFacade(lighting, security, climate, entertainment);

    @Test
    void movieModeCallsAllSubsystemsInSequence() {
        var response = facade.activateMovieMode();

        assertEquals("Movie Night", response.getScene());
        assertEquals(9, response.getActions().size());

        InOrder order = inOrder(lighting, entertainment, climate, security);
        order.verify(lighting).setBrightness(15);
        order.verify(lighting).setScene("cinema-amber");
        order.verify(entertainment).closeBlinds();
        order.verify(entertainment).turnOnTV();
        order.verify(entertainment).enableSurroundSound();
        order.verify(entertainment).setVolume(65);
        order.verify(climate).setTemperature(22);
        order.verify(climate).setFanSpeed("low");
        order.verify(security).disableCameras();
        order.verifyNoMoreInteractions();
    }

    @Test
    void leaveModeLocksAndArmsAfterTurningThingsOff() {
        var response = facade.activateLeaveMode();

        assertEquals("Leaving Home", response.getScene());
        assertEquals(9, response.getActions().size());

        InOrder order = inOrder(lighting, entertainment, climate, security);
        order.verify(lighting).turnOff();
        order.verify(entertainment).turnOffTV();
        order.verify(entertainment).stopMusic();
        order.verify(entertainment).closeBlinds();
        order.verify(climate).enableEcoMode();
        order.verify(security).lockAllDoors();
        order.verify(security).arm();
        order.verify(security).enableCameras();
        order.verify(security).enablePerimeterAlerts();
        order.verifyNoMoreInteractions();
    }

    @Test
    void failedSubsystemStopsSceneWithoutClaimingSuccess() {
        doThrow(new IllegalStateException("simulated TV fault"))
                .when(entertainment).turnOnTV();

        IllegalStateException error =
                assertThrows(IllegalStateException.class, facade::activateMovieMode);
        assertEquals("simulated TV fault", error.getMessage());

        verify(lighting).setBrightness(15);
        verify(lighting).setScene("cinema-amber");
        verify(entertainment).closeBlinds();
        verify(entertainment).turnOnTV();
        verify(entertainment, never()).enableSurroundSound();
        verifyNoInteractions(climate, security);
    }
}
