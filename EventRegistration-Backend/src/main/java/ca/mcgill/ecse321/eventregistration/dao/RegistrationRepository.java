package ca.mcgill.ecse321.eventregistration.dao;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    List<Registration> findByParticipant(Person personName);
    boolean existsByParticipantAndEvent(Person person, Event eventName);
    Registration findByParticipantAndEvent(Person person, Event eventName);
}
