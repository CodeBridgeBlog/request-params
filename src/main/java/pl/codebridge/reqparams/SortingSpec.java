package pl.codebridge.reqparams;

import static pl.codebridge.reqparams.SortingSpec.Property.ID;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@ToString
@RequireSingleSortParam
public class SortingSpec {

    public static final Property DEFAULT_PROPERTY = ID;
    public static final Sort.Direction DEFAULT_DIRECTION = ASC;

    @Getter
    @SupportedSortingProperty
    private final String sortAsc;

    @Getter
    @SupportedSortingProperty
    private final String sortDesc;

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    private final Optional<Order> order = initOrder(sortAsc, sortDesc);

    public SortingSpec(String sortAsc, String sortDesc) {
        this.sortAsc = sortAsc;
        this.sortDesc = sortDesc;
    }

    public Property getProperty() {
        return getOrder().map(Order::getProperty).orElse(null);
    }

    public Sort.Direction getDirection() {
        return getOrder().map(Order::getDirection).orElse(null);
    }

    private Optional<Order> initOrder(String sortAsc, String sortDesc) {
        try {
            return Optional.of(new Order(sortAsc, sortDesc));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    public enum Property { ID, NUMBER, TEXT }

    @Getter
    @ToString
    private static class Order {

        private final Property property;
        private final Sort.Direction direction;

        private Order(String sortAsc, String sortDesc) {
            if (sortDesc != null) {
                property = Property.valueOf(sortDesc.toUpperCase());
                direction = DESC;
            } else if (sortAsc != null) {
                property = Property.valueOf(sortAsc.toUpperCase());
                direction = ASC;
            } else {
                property = DEFAULT_PROPERTY;
                direction = DEFAULT_DIRECTION;
            }
        }

    }

}
