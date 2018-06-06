package tech.letscode.challenge.rtmessaging.port.adapter.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@RestController
public class DummyJsonResource
{
    @GetMapping("/dummies")
    public ResponseEntity<Void> return200()
    {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
