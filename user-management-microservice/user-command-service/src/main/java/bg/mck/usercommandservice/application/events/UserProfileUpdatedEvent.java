package bg.mck.usercommandservice.application.events;


import bg.mck.usercommandservice.application.enums.EvenType;


public class UserProfileUpdatedEvent extends BaseEvent{

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public UserProfileUpdatedEvent(EvenType evenType) {
        super(evenType);
    }


    public String getFirstName() {
        return firstName;
    }

    public UserProfileUpdatedEvent setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileUpdatedEvent setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserProfileUpdatedEvent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
