package ca.mcgill.ecse321.eventregistration.dto;

public class RegistrationDto {

    private PersonDto participant;
    private EventDto event;

    public RegistrationDto() {
    }

    public RegistrationDto(PersonDto participant, EventDto event) {
        this.participant = participant;
        this.event = event;
    }

    public PersonDto getParticipant() {
        return participant;
    }

    public void setParticipant(PersonDto participant) {
        this.participant = participant;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }
}