package tech.letscode.challenge.rtmessaging.domain.model;

import javax.annotation.Nonnull;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.letscode.challenge.rtmessaging.shared.Args;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@Document(collection = "dummies")
@Getter
public class Dummy
{
    @Id
    private String id;

    private String message;

    public Dummy(@Nonnull String id, @Nonnull String message)
    {
        this.id = Args.notNull(id, "id is required");
        this.message = Args.notNull(message, "message is required");
    }
}
