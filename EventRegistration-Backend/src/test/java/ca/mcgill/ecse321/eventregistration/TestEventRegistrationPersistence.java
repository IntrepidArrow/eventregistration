package ca.mcgill.ecse321.eventregistration;

import ca.mcgill.ecse321.eventregistration.dao.EventRepository;
import ca.mcgill.ecse321.eventregistration.dao.PersonRepository;
import ca.mcgill.ecse321.eventregistration.dao.RegistrationRepository;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEventRegistrationPersistence {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RegistrationRepository registrationRepository;

    @AfterEach
    public void clearDatabase() {
        // Fisrt, we clear registrations to avoid exceptions due to inconsistencies
        registrationRepository.deleteAll();
        // Then we can clear the other tables
        personRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadPerson() {
        String name = "TestPerson";
        // First example for object save/load
        Person person = new Person();
        // First example for attribute save/load
        person.setName(name);
        personRepository.save(person);

        person = null;

        person = personRepository.findPersonByName(name);
        assertNotNull(person);
        assertEquals(name, person.getName());
    }

    @Test
    public void testPersistAndLoadEvent() {
        String name = "ECSE321 Tutorial";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
        Event event = new Event();
        event.setName(name);
        event.setEventDate(date);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        eventRepository.save(event);

        event = null;

        event = eventRepository.findEventByName(name);

        assertNotNull(event);
        assertEquals(name, event.getName());
        assertEquals(date, event.getEventDate());
        assertEquals(startTime, event.getStartTime());
        assertEquals(endTime, event.getEndTime());
    }

    @Test
    public void testPersistAndLoadRegistration() {
        String personName = "TestPerson";
        Person person = new Person();
        person.setName(personName);
        personRepository.save(person);

        String eventName = "ECSE321 Tutorial";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
        Event event = new Event();
        event.setName(eventName);
        event.setEventDate(date);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        eventRepository.save(event);

        Registration reg = new Registration();
        int regId = 1;
        // First example for reference save/load
        reg.setId(regId);
        reg.setParticipant(person);
        reg.setEvent(event);
        registrationRepository.save(reg);

        reg = null;

        reg = registrationRepository.findByParticipantAndEvent(person, event);
        assertNotNull(reg);
        assertEquals(regId, reg.getId());
        // Comparing by keys
        assertEquals(person.getName(), reg.getParticipant().getName());
        assertEquals(event.getName(), reg.getEvent().getName());
    }

}