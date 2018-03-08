package spring.tutorial.model.pojo.google.matrix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceElement {

    private String status;
    private Distance distance;
    private Duration duration;
}
