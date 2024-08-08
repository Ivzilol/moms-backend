package bg.mck.usercommandservice.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatusUpdatedDTO {

    @JsonProperty("isActive")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public UserStatusUpdatedDTO setActive(boolean active) {
        isActive = active;
        return this;
    }
}
