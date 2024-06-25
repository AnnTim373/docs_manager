package org.timofeeva.docs.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponse {

    Long id;

    String result;

    boolean isSuccess;

    String errorText;

}
