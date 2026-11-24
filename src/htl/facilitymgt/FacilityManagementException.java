package htl.facilitymgt;

import java.io.IOException;

public class FacilityManagementException extends IOException {
    public FacilityManagementException(String message) {
        super(message);
    }

    public FacilityManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
