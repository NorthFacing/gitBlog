package y2023.m03.d11.s02;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credit {
    private Asset assets;
    private Liability liabilities;
}