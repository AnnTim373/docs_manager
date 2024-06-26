package org.timofeeva.docs.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Единый ответ при успешном выполнении запроса")
public class SuccessResponse {

    @Schema(description = "Идентификатор записи")
    private Long id;

    @Schema(description = "Результат")
    private String result;

}
