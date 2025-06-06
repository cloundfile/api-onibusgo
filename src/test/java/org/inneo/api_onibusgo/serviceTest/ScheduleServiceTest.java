package org.inneo.api_onibusgo.serviceTest;

import org.mockito.*;
import java.util.List;
import org.mockito.MockedStatic;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

import org.inneo.api_onibusgo.domains.Schedule;
import org.inneo.api_onibusgo.ferramentas.utils;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.inneo.api_onibusgo.repositories.ScheduleRep;

import org.inneo.api_onibusgo.services.ScheduleService;
import org.springframework.data.jpa.domain.Specification;
import org.inneo.api_onibusgo.configuration.MessageException;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {
	@Mock
    private ScheduleRep scheduleRep;


    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;

    @BeforeEach
    void setup() {
        schedule = new Schedule();
        schedule.setId(1L);
        schedule.setCodigo("TEST123");
        schedule.setPartida("08:00");
        schedule.setDestino("09:00");
        schedule.setRetorno("10:00");
    }

    @Test
    void testCreate() {
        when(scheduleRep.saveAndFlush(schedule)).thenReturn(schedule);

        Schedule created = scheduleService.create(schedule);

        assertEquals(schedule, created);
        verify(scheduleRep, times(1)).saveAndFlush(schedule);
    }

    @Test
    void testUpdateExistingSchedule() {
        Schedule newData = new Schedule();
        newData.setId(1L);
        newData.setCodigo("NEWCODE");

        // mock getReferenceById para retornar schedule atual
        when(scheduleRep.getReferenceById(1L)).thenReturn(schedule);
        when(scheduleRep.saveAndFlush(any(Schedule.class))).thenAnswer(i -> i.getArgument(0));

        Schedule updated = scheduleService.update(newData);

        assertEquals(newData.getCodigo(), updated.getCodigo());
        verify(scheduleRep).saveAndFlush(updated);
    }

    @Test
    void testUpdateWhenScheduleNotFound() {
        Schedule newData = new Schedule();
        newData.setId(2L);
        newData.setCodigo("NEWCODE2");

        // simula getReferenceById retornando null
        when(scheduleRep.getReferenceById(2L)).thenReturn(null);
        when(scheduleRep.saveAndFlush(any(Schedule.class))).thenAnswer(i -> i.getArgument(0));

        Schedule updated = scheduleService.update(newData);

        assertEquals(newData.getCodigo(), updated.getCodigo());
        verify(scheduleRep).saveAndFlush(updated);
    }

    @Test
    void testFindAllOnWeekday() {
        when(scheduleRep.findAll()).thenReturn(List.of(schedule));

        List<Schedule> allSchedules = scheduleService.findAll();

        assertFalse(allSchedules.isEmpty());
        verify(scheduleRep).findAll();
    }

    @Test
    void testFindAllOnWeekendThrowsException() {
    	try (MockedStatic<utils> utilsMock = Mockito.mockStatic(utils.class)) {
    	    utilsMock.when(utils::isWeekend).thenReturn(true);

    	        MessageException thrown = assertThrows(MessageException.class, () -> {
    	            scheduleService.findAll();
    	        });

    	        assertEquals("Horários de transporte não estão disponíveis durante os finais de semana.", thrown.getMessage());
    	    }
    }

    @SuppressWarnings("unchecked")
	@Test
    void testFindBy() {
        String codigo = "TEST123";

        // Mocka a chamada do Specification e retorno do repository
        when(scheduleRep.findAll(any(Specification.class))).thenReturn(List.of(schedule));

        List<Schedule> result = scheduleService.findBy(codigo);

        assertFalse(result.isEmpty());
        verify(scheduleRep).findAll(any(Specification.class));
    }

    @Test
    void testDeleteWhenScheduleExists() {
        when(scheduleRep.getReferenceById(1L)).thenReturn(schedule);

        doNothing().when(scheduleRep).deleteById(1L);

        scheduleService.delete(1L);

        verify(scheduleRep).deleteById(1L);
    }

    @Test
    void testDeleteWhenScheduleDoesNotExist() {
        when(scheduleRep.getReferenceById(2L)).thenReturn(null);

        scheduleService.delete(2L);

        verify(scheduleRep, never()).deleteById(anyLong());
    }
}
