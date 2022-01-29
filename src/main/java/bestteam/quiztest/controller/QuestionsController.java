package bestteam.quiztest.controller;

import bestteam.quiztest.dto.ApiResponse;
import bestteam.quiztest.dto.QuestionsDto;
import bestteam.quiztest.model.Questions;
import bestteam.quiztest.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> add(@RequestBody QuestionsDto questionsDto){
        ApiResponse apiResponse = questionsService.add(questionsDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLEADMIN')")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody QuestionsDto questionsDto){
        ApiResponse response = questionsService.edit(id, questionsDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        List<Questions> questions = questionsService.getAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/one/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Questions questions = questionsService.getOneById(id);
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = questionsService.delete(id);
                return ResponseEntity.ok(response);
    }



}
