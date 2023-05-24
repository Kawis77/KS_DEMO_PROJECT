package com.example.demo.menucomponent.service;

import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import com.example.demo.menucomponent.dao.repository.MenuDocumentComponentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class MenuDocumentComponentService {

    @Autowired
    private MenuDocumentComponentRepository documentComponentRepository;


    public MenuDocumentComponentEntity create(MenuDocumentComponentEntity documentComponent) {
        if (documentComponent != null) {
            return documentComponentRepository.save(documentComponent);

        } else {
            log.debug("Is not possible to create new MenuDocument , object is null");
            return null;
        }
    }


    public MenuDocumentComponentEntity edit(MenuDocumentComponentEntity documentComponent) {
        if (documentComponent != null) {
            return documentComponentRepository.save(documentComponent);

        } else {
            log.debug("Is not possible to edit MenuDocument");
            return null;
        }
    }


    public void delete(Long aId) {

        Optional<MenuDocumentComponentEntity> menuDocumentComponentEntity = documentComponentRepository.findById(aId);

        if (menuDocumentComponentEntity.isPresent()) {
            MenuDocumentComponentEntity menuDocumentComponent = menuDocumentComponentEntity.get();
            documentComponentRepository.delete(menuDocumentComponent);
        } else {
            throw new EntityNotFoundException("MenuDocumentComponent not found");
        }

    }


    public List<MenuDocumentComponentEntity> readAll() {
        return documentComponentRepository.findAll();
    }


    public MenuDocumentComponentEntity readWithId(Long id) {
        if (id != null) {

            Optional<MenuDocumentComponentEntity> optionalMenuDocumentComponent = documentComponentRepository.findById(id);
            if (optionalMenuDocumentComponent.isPresent()) {

                return optionalMenuDocumentComponent.get();
            } else {

                log.debug("Is not possible to read MenuDocumentComponent with id : " + id);
                return null;
            }
        }
        return null;
    }
}
