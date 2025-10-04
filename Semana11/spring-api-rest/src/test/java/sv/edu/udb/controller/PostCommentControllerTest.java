package sv.edu.udb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;
import sv.edu.udb.service.PostCommentService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostCommentController.class)
class PostCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostCommentService service;

    @Test
    void shouldCreateComment() throws Exception {
        PostCommentRequest req = PostCommentRequest.builder()
                .postId(1L).review("hey").commentDate(LocalDate.of(2024,1,1)).build();

        when(service.save(any(PostCommentRequest.class)))
                .thenReturn(PostCommentResponse.builder().id(1L).review("hey").postId(1L).build());

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.review").value("hey"))
                .andExpect(jsonPath("$.postId").value(1L));
    }

    @Test
    void shouldListByPost() throws Exception {
        when(service.findAllByPostId(1L)).thenReturn(List.of(
                PostCommentResponse.builder().id(1L).review("a").postId(1L).build()
        ));
        mockMvc.perform(get("/comments").param("postId","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(
                        List.of(PostCommentResponse.builder().id(1L).review("a").postId(1L).build())
                )));
    }
}
