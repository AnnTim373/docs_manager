package org.timofeeva.docs.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class NotFoundException extends RuntimeException {

    private String field;

}
