package pl.codebridge.reqparams;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class FooDao {

    private static final Map<Integer, Foo> fooStorage = initFooStorage();

    public List<Foo> getFoos(FooRequestParams requestParams) {
        return fooStorage.values().stream()
                .sorted(comparator(requestParams.getSortingSpec()))
                .skip(requestParams.getPaginationSpec().getOffset())
                .limit(requestParams.getPaginationSpec().getLimit())
                .collect(toList());
    }

    private static Map<Integer, Foo> initFooStorage() {
        return Stream.of(
                new Foo(0, "jkl", 3.4),
                new Foo(1, "mno", 2.1),
                new Foo(2, "def", 6.4),
                new Foo(3, "abc", 9.8),
                new Foo(4, "ghi", 1.9)
        ).collect(toMap(Foo::getId, identity()));
    }

    private static Comparator<Foo> comparator(SortingSpec sortingSpec) {
        Comparator<Foo> comparator;
        switch (sortingSpec.getProperty()) {
            case ID:
                comparator = comparing(Foo::getId);
                break;
            case TEXT:
                comparator = comparing(Foo::getText);
                break;
            case NUMBER:
                comparator = comparing(Foo::getNumber);
                break;
            default:
                throw new IllegalArgumentException(sortingSpec.toString());
        }
        if (sortingSpec.getDirection() == DESC) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

}
