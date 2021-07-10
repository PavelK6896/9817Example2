package app.web.pavelk.lombok1;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Value
@Jacksonized
@With
@Builder
public class D1 {

    @An1
    String s1;

    @An2
    String s2;

    @An3
    F1 f1;

    List<Long> l1;

}
