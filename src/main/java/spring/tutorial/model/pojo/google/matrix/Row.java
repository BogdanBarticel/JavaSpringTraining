package spring.tutorial.model.pojo.google.matrix;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class Row {

    private List<DistanceElement> elements;
}
