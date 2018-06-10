package tech.letscode.challenge.rtmessaging.shared;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public final class Args
{
    public static <T> T notNull(T object, String message)
    {
        if (object == null)
        {
            throw new IllegalArgumentException(message);
        } else
        {
            return object;
        }
    }
}
