package org.timofeeva.docs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.timofeeva.docs.domain.Document;
import org.timofeeva.docs.dto.DocumentDTO;
import org.timofeeva.docs.dto.DocumentView;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface DocumentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "executor", source = "executor")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "deadline", source = "deadline")
    @Mapping(target = "state", source = "state")
    DocumentView toView(Document document);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "executor", source = "executor")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "deadline", source = "deadline")
    @Mapping(target = "state", source = "state")
    Document fromDTO(DocumentDTO dto);

}
