package com.facade.controller;

import com.facade.facade.HomeAutomationFacade;
import com.facade.model.FacadeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HomeControllerTest {
    private final HomeAutomationFacade facade = mock(HomeAutomationFacade.class);
    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new HomeController(facade)).build();

    @Test
    void selectsSceneCaseInsensitively() throws Exception {
        when(facade.activateMovieMode())
                .thenReturn(new FacadeResponse("Movie Night", "Simulated", List.of()));

        mvc.perform(post("/api/home/scene/MOVIE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scene").value("Movie Night"));
        verify(facade).activateMovieMode();
    }

    @Test
    void invalidSceneReturnsStructured400AndCallsNoSubsystem() throws Exception {
        mvc.perform(post("/api/home/scene/invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Unknown scene: invalid"));
        verifyNoInteractions(facade);
    }

    @Test
    void sceneDiscoveryExposesAvailableOptions() throws Exception {
        mvc.perform(get("/api/home/scenes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("movie"))
                .andExpect(jsonPath("$[4].id").value("party"));
    }
}
