package tech.letscode.challenge.rtmessaging.port.adapter.api;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.letscode.challenge.rtmessaging.application.DummyApplicationService;
import tech.letscode.challenge.rtmessaging.domain.model.Dummy;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@RestController
@RequiredArgsConstructor
public class DummyJsonResource
{
    private final DummyApplicationService dummyApplicationService;

    @GetMapping("/dummies")
    public PagedResources<Resource<Dummy>> getDummies(@PageableDefault Pageable pageable,
                                                      PagedResourcesAssembler<Dummy> assembler)
    {
        Page<Dummy> page = this.dummyApplicationService.dummies(pageable);
        return assembler.toResource(page);
    }

    @PostMapping(value = "/dummies")
    public ResponseEntity<Void> createNewDummy(@RequestBody @Valid PostDummyModel payload)
    {
        this.dummyApplicationService.createDummy(payload.getMessage());
        return ResponseEntity.accepted().build();
    }
}
