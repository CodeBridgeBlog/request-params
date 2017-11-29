package pl.codebridge.reqparams;

import javax.validation.Valid;
import lombok.Getter;
import lombok.ToString;

@ToString
public class FooRequestParams {

    @Getter
    @Valid
    private final PaginationSpec paginationSpec;

    @Getter
    @Valid
    private final SortingSpec sortingSpec;

    public FooRequestParams(Integer limit, Integer offset, String sortAsc, String sortDesc) {
        this.paginationSpec = new PaginationSpec(limit, offset);
        this.sortingSpec = new SortingSpec(sortAsc, sortDesc);
    }

}
