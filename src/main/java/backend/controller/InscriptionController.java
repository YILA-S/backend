package backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InscriptionController {

    @PostMapping("/course/{courseId}/sections/{sectionId}/{periodId}")
    public ResponseEntity createInsciptions(@PathVariable("courseId") String courseId,
                                            @PathVariable("sectionId") String sectionId,
                                            @PathVariable("periodId") String periodId){
        return null;
    }
}
