package pl.codebridge.reqparams;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foos")
@RequiredArgsConstructor
@Slf4j
public class FooResource {

    private final FooDao fooDao;

    @GetMapping
    public List<Foo> getFoos(FooRequestParams params) {
        log.debug(params.toString());
        return fooDao.getFoos(params);
    }

}
