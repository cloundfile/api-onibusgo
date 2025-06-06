package org.inneo.api_onibusgo.controllerTest;

import java.util.List;
import org.mockito.Mock;
import java.util.Collections;

import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.doThrow;
import org.springframework.http.HttpStatus;
import static org.mockito.Mockito.doNothing;

import org.inneo.api_onibusgo.domains.Schedule;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.inneo.api_onibusgo.services.ScheduleService;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.inneo.api_onibusgo.controllers.ScheduleController;

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {
	@InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleService scheduleService;
    
    private Schedule schedule;
    private Long scheduleID;
    
    @BeforeEach
    void setup() {
    	schedule = new Schedule();
        schedule.setCodigo("TEST123");
        schedule.setPartida("00:00");
        schedule.setDestino("00:01");
        schedule.setRetorno("00:03");
    }
    

    @Test
    void testCreateAndFindBySuccess() {

        when(scheduleService.create(schedule)).thenReturn(schedule);
        when(scheduleService.findBy("TEST123")).thenReturn(List.of(schedule));

        ResponseEntity<?> createResponse = scheduleController.create(schedule);

        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        assertEquals(schedule, createResponse.getBody());

        ResponseEntity<?> findResponse = scheduleController.findBy("TEST123");

        assertEquals(HttpStatus.OK, findResponse.getStatusCode());

        @SuppressWarnings("unchecked")
        List<Schedule> scheduleList = (List<Schedule>) findResponse.getBody();
        assertEquals(1, scheduleList.size());

        scheduleID = scheduleList.get(0).getId(); 
    }

    @Test
    void testCreateThrowsException() {
        when(scheduleService.create(schedule)).thenThrow(new RuntimeException("Erro ao salvar"));

        ResponseEntity<?> response = scheduleController.create(schedule);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro ao salvar", response.getBody());
    }
    
    @Test
    void testUpdateSuccess() {
        schedule.setId(scheduleID);
        when(scheduleService.update(schedule)).thenReturn(schedule);

        ResponseEntity<?> response = scheduleController.update(schedule);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schedule, response.getBody());
    }

    @Test
    void testUpdateThrowsException() {
        when(scheduleService.update(schedule)).thenThrow(new RuntimeException("Erro ao atualizar"));

        ResponseEntity<?> response = scheduleController.update(schedule);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro ao atualizar", response.getBody());
    }
    
    @Test
    void testFindAllSuccessWithData() {
        when(scheduleService.findAll()).thenReturn(List.of(schedule));

        ResponseEntity<?> response = scheduleController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        @SuppressWarnings("unchecked")
        List<Schedule> responseBody = (List<Schedule>) response.getBody();
        assertEquals(1, responseBody.size());
        assertEquals(schedule, responseBody.get(0));
    }
    
    @Test
    void testFindAllReturnsNoContentWhenEmpty() {

        when(scheduleService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = scheduleController.findAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    void testFindAllThrowsExceptionReturnsBadRequest() {
        when(scheduleService.findAll()).thenThrow(new RuntimeException("Erro simulado"));

        ResponseEntity<?> response = scheduleController.findAll();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro simulado", response.getBody());
    }
    
    @Test
    void testFindByReturnsData() {
        when(scheduleService.findBy("TEST123")).thenReturn(List.of(schedule));

        ResponseEntity<?> response = scheduleController.findBy("TEST123");

        assertEquals(HttpStatus.OK, response.getStatusCode());

        @SuppressWarnings("unchecked")
        List<Schedule> result = (List<Schedule>) response.getBody();
        assertEquals(1, result.size());
        assertEquals(schedule, result.get(0));
    }

    @Test
    void testFindByReturnsNoContent() {
        when(scheduleService.findBy("TEST123")).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = scheduleController.findBy("TEST123");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testFindByThrowsException() {
        when(scheduleService.findBy("TEST123")).thenThrow(new RuntimeException("Erro ao buscar"));

        ResponseEntity<?> response = scheduleController.findBy("TEST123");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro ao buscar", response.getBody());
    }
    
    @Test
    void testDeleteSuccess() {
        Long id = 1L;
        doNothing().when(scheduleService).delete(id);

        ResponseEntity<?> response = scheduleController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deletado com sucesso!", response.getBody());
    }

    @Test
    void testDeleteThrowsException() {
        Long id = 1L;
        doThrow(new RuntimeException("Erro ao deletar")).when(scheduleService).delete(id);

        ResponseEntity<?> response = scheduleController.delete(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro ao deletar", response.getBody());
    }
}
