package bg.mck.usercommandservice.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatusDTO {

    @JsonProperty("isActive")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public UserStatusDTO setActive(boolean active) {
        isActive = active;
        return this;
    }
}
