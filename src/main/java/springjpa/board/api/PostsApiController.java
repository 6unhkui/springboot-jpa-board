package springjpa.board.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springjpa.board.service.PostsService;
import springjpa.board.web.dto.PostsResponseDto;
import springjpa.board.web.dto.PostsSaveRequestDto;
import springjpa.board.web.dto.PostsUpdateRequestDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @GetMapping("/api/v1/posts")
    public List<PostsResponseDto> findAll() {
        return postsService.findAllDesc();
    }

    @PostMapping("/api/v1/posts")
    public ResponseEntity save(@Valid PostsSaveRequestDto requestDto, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(postsService.save(requestDto));
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id,
                       PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void delete(@PathVariable("id") Long id){
        postsService.deleteById(id);
    }
}
