package sachith.dev.studentmanagmentservice.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonJsonResponse<T> {
    private boolean success;
    private Object data;
    private List<T> dataList;
    private int numberOfRecords;
    private int noOfPages;
    private Object entityObject;
}
