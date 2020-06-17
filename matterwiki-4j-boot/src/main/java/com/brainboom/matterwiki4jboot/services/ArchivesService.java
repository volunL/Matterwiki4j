package com.brainboom.matterwiki4jboot.services;

import com.brainboom.matterwiki4jboot.dto.ArchivesDto;

import java.util.List;

public interface ArchivesService {

    ArchivesDto GetArchivesById(int id);

    List<ArchivesDto> GetAllArchivesNoPages();

    List<ArchivesDto> GetAllArchivesByArticleId(int id);


}
