package pl.codebridge.reqparams;

import java.util.Optional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaginationSpec {

    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_OFFSET = 0;

    @Min(1)
    @Max(100)
    private final Integer limit;

    @Min(0)
    private final Integer offset;

    public PaginationSpec(Integer limit, Integer offset) {
        this.limit = Optional.ofNullable(limit).orElse(DEFAULT_LIMIT);
        this.offset = Optional.ofNullable(offset).orElse(DEFAULT_OFFSET);
    }

}
