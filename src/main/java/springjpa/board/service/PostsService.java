package springjpa.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpa.board.domain.posts.Posts;
import springjpa.board.domain.posts.PostsRepository;
import springjpa.board.web.dto.PostsResponseDto;
import springjpa.board.web.dto.PostsSaveRequestDto;
import springjpa.board.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
       return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return posts.getId();
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    public List<PostsResponseDto> findAllDesc() {
       return postsRepository.findAllDesc().stream().map(PostsResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        postsRepository.deleteById(id);
    }
}
