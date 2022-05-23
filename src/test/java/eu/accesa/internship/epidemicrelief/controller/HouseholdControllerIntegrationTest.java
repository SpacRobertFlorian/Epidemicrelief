package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.Launcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Launcher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HouseholdControllerIntegrationTest {

    private static final String GET_HOUSEHOLDS_URL = "/households";
    private static final String SAVE_HOUSEHOLD_URL = "/households/save";
    private static final String HOUSEHOLDS_ATTRIBUTE = "households";
    private static final String GET_HOUSEHOLDS_EXPECTED_VIEW_NAME = "household/householdList";
    private static final String REPRESENTATIVE_PARAMETER = "representative";
    private static final String REPRESENTATIVE_PARAMETER_VALUE = "Test Representative 2";
    private static final String PHONE_PARAMETER = "phone";
    private static final String PHONE_PARAMETER_VALUE = "0234657893";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenHouseholdsUrl_whenGelAllHouseholds_thenPerformGet() throws Exception {
        mockMvc.perform(get(GET_HOUSEHOLDS_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(HOUSEHOLDS_ATTRIBUTE))
                .andExpect(model().attribute(HOUSEHOLDS_ATTRIBUTE, hasSize(4)))
                .andExpect(view().name(GET_HOUSEHOLDS_EXPECTED_VIEW_NAME));
    }

    @Test
    public void givenValidHouseholdData_whenSaveHousehold_thenPerformPost() throws Exception {
        mockMvc.perform(post(SAVE_HOUSEHOLD_URL)
                        .params(fillParameters())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());
    }

    private MultiValueMap<String, String> fillParameters() {
        MultiValueMap<String, String> params = new HttpHeaders();
        params.add(REPRESENTATIVE_PARAMETER, REPRESENTATIVE_PARAMETER_VALUE);
        params.add(PHONE_PARAMETER, PHONE_PARAMETER_VALUE);
        return params;
    }
}