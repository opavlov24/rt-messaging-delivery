package tech.letscode.challenge.rtmessaging.port.adapter.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@Data
class PostDummyModel
{
    @NotEmpty(message = "The message field must have at least 1 symbol.")
    private String message;
}
