package lk.ijse.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookIdWithTitle {
    private long isbn;
    private String title;

    @Override
    public String toString(){
        return isbn + " - " + title;
    }
}
