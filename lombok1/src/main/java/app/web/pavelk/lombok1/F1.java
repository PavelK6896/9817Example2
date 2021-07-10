package app.web.pavelk.lombok1;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class F1 {
    String q1;
}
