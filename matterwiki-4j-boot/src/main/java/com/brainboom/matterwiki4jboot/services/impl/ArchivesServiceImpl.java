package com.brainboom.matterwiki4jboot.services.impl;

import com.brainboom.matterwiki4jboot.entity.Archives;
import com.brainboom.matterwiki4jboot.entity.Articles;
import com.brainboom.matterwiki4jboot.repository.ArchivesRepository;
import com.brainboom.matterwiki4jboot.services.ArchivesService;
import com.brainboom.matterwiki4jboot.dto.ArchivesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    private ArchivesRepository archivesRepository;


    @Override
    public ArchivesDto GetArchivesById(int id) {
        Archives archives = archivesRepository.findArchivesById(id);
        ArchivesDto archivesDto = ArchivesDto.convertFromArchives(archives);
        return archivesDto;
    }

    @Override
    public List<ArchivesDto> GetAllArchivesNoPages() {
        List<Archives> archivesList = archivesRepository.findAll();
        List<ArchivesDto> archivesDtoList = ArchivesDto.convertFromArchivesList(archivesList);
        return archivesDtoList;
    }

    @Override
    public List<ArchivesDto> GetAllArchivesByArticleId(int id) {
        List<Archives> archivesList = archivesRepository.findAllByArticlesByArticleIdOrderByUpdatedAtDesc(new Articles(id));
        List<ArchivesDto> archivesDtoList = ArchivesDto.convertFromArchivesList(archivesList);
        return archivesDtoList;
    }
}
