package app.web.pavelk.word2;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Alphabet {
    private String letter;
    private List<Word2> words;
}
