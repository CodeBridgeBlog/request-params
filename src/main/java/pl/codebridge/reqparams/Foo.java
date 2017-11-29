package pl.codebridge.reqparams;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Foo {

    private final Integer id;

    private final String text;

    private final Double number;

}
