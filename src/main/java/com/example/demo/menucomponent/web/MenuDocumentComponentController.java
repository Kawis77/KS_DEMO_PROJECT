package com.example.demo.menucomponent.web;

import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import com.example.demo.menucomponent.dao.form.MenuDocumentComponentForm;
import com.example.demo.menucomponent.service.MenuDocumentComponentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/api")
public class MenuDocumentComponentController {


    @Autowired
    private MenuDocumentComponentService menuDocumentComponentService;


    @GetMapping("/menu/component/all")
    public ResponseEntity<List<MenuDocumentComponentEntity>> getAllMenuComponents() {

        List<MenuDocumentComponentEntity> menuDocumentComponentEntityList = menuDocumentComponentService.readAll();

        if (menuDocumentComponentEntityList != null) {

            return ResponseEntity.ok(menuDocumentComponentEntityList);
        } else {

            log.debug("List with all MenuDocumentComponent is null");
            return null;
        }
    }


    @PostMapping("/menu/component/create")
    public ResponseEntity<Object> createMenuDocumentComponent(@RequestBody MenuDocumentComponentForm menuDocumentComponentForm) {

        if (menuDocumentComponentForm != null) {
            MenuDocumentComponentEntity menuDocumentComponent = new MenuDocumentComponentEntity();
            menuDocumentComponent.setName(menuDocumentComponentForm.getName());
            menuDocumentComponent.setDescription(menuDocumentComponentForm.getDescription());

                menuDocumentComponentService.create(menuDocumentComponent);
                return ResponseEntity.ok().build();

        } else {
            log.debug("Is not possible to create a new MenuDocumentComponent");
            return ResponseEntity.badRequest().build();
        }

    }

}
