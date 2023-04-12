package br.com.planonegocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.hackathon.planonegocio.entities.topic.businessrule.*;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.TopicGatewayImpl;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model.GetTopicResponse;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.controller.model.GetTopicResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TopicControllerTest {

    @Mock
    private SaveTopic saveTopic;

    @Mock
    private UpdateTopic updateTopic;

    @Mock
    private GetAllTopic getAllTopic;

    @Mock
    private FindOneTopic findOneTopic;

    @Mock
    private DeleteTopic deleteTopic;

    @Mock
    private SearchAllPageTopic searchAllPageTopic;

    @Mock
    private FilterTopic filterTopic;

    @Mock
    private GetTopicResponseMapper getTopicResponseMapper;

    @InjectMocks
    private TopicGatewayImpl topicService;

    private Topic topic;

    private Topic topic1;

    private GetTopicResponse getTopicResponse;

    private GetTopicResponse getTopicResponse1;

    private List<Topic> topics;

    private List<GetTopicResponse> expectedResponses;

    private PageImpl<Topic> topicPage;

    private PageImpl<GetTopicResponse> topicResponsePage;

    @BeforeEach
    void setUp() {
        // Set up the mock objects
        topic = new Topic();
        topic.setId(1L);
        topic.setDescription("This is a test topic");

        topic1 = new Topic();
        topic1.setId(2L);
        topic1.setDescription("This is a test topic1");

        getTopicResponse = new GetTopicResponse();
        getTopicResponse.setId(1L);
        getTopicResponse.setDescription("This is a test topic");

        getTopicResponse1 = new GetTopicResponse();
        getTopicResponse1.setId(2L);
        getTopicResponse1.setDescription("This is a test topic1");

        topics = new ArrayList<>();
        topics.add(topic);
        topics.add(topic1);

        expectedResponses = new ArrayList<>();
        expectedResponses.add(getTopicResponse);
        expectedResponses.add(getTopicResponse1);

        topicPage = new PageImpl<>(topics, PageRequest.of(0, 2), topics.size());
        topicResponsePage = new PageImpl<>(expectedResponses, PageRequest.of(0, 2), expectedResponses.size());
    }

    @Test
    void testSaveTopic() {

        when(saveTopic.saveTopic(topic)).thenReturn(topic);
        when(getTopicResponseMapper.modelToEntity(topic)).thenReturn(getTopicResponse);
        // Call the method under test
        GetTopicResponse result = topicService.saveTopic(topic);

        // Verify the results
        assertEquals(getTopicResponse, result);
        verify(saveTopic, times(1)).saveTopic(topic);
        verify(getTopicResponseMapper, times(1)).modelToEntity(topic);
    }

    @Test
    void testUpdateTopic() {
        // Create a test topic
        Topic topic = new Topic();
        topic.setId(1L);
        topic.setDescription("This is a test topic.");

        // Create a test response
        Topic updatedTopic = new Topic();
        updatedTopic.setId(1L);
        updatedTopic.setDescription("This is an updated test topic.");
        GetTopicResponse response = new GetTopicResponse();
        response.setId(1L);
        response.setDescription("This is an updated test topic.");

        // Mock the behavior of the UpdateTopic and GetTopicResponseMapper dependencies
        when(updateTopic.updateTopic(topic)).thenReturn(updatedTopic);
        when(getTopicResponseMapper.modelToEntity(updatedTopic)).thenReturn(response);

        // Call the method being tested
        GetTopicResponse result = topicService.updateTopic(topic);

        // Verify the result
        assertEquals(response, result);

        // Verify that the dependencies were called with the correct arguments
        verify(updateTopic).updateTopic(topic);
        verify(getTopicResponseMapper).modelToEntity(updatedTopic);
    }

    @Test
    public void testGetAllTopic() {
        // mock the behavior of getAllTopic
        when(getAllTopic.getAllTopic()).thenReturn(topics);

        // mock the behavior of getTopicResponseMapper
        when(getTopicResponseMapper.modelToEntity(topics.get(0))).thenReturn(expectedResponses.get(0));
        when(getTopicResponseMapper.modelToEntity(topics.get(1))).thenReturn(expectedResponses.get(1));

        // call the method being tested
        List<GetTopicResponse> actualResponses = topicService.getAllTopic();

        // verify the results
        assertEquals(expectedResponses.size(), actualResponses.size());
        assertEquals(expectedResponses.get(0), actualResponses.get(0));
        assertEquals(expectedResponses.get(1), actualResponses.get(1));
    }

    @Test
    void testFindOneTopic() {
        // Mock the findOneTopic method to return a Topic object
        when(findOneTopic.findOneTopic(1L)).thenReturn((topic));

        // Mock the modelToEntity method to return a GetTopicResponse object
        GetTopicResponse expectedResponse = new GetTopicResponse();
        when(getTopicResponseMapper.modelToEntity(topic)).thenReturn(expectedResponse);

        // Call the findOneTopic method and verify the result
        GetTopicResponse actualResponse = topicService.findOneTopic(1L);
        assertEquals(expectedResponse, actualResponse);

        // Verify that the findOneTopic method was called once with the correct argument
        verify(findOneTopic, times(1)).findOneTopic(1L);

        // Verify that the modelToEntity method was called once with the correct argument
        verify(getTopicResponseMapper, times(1)).modelToEntity(topic);
    }

    @Test
    public void testDeleteTopic() {
        Long id = 123L;

        doNothing().when(deleteTopic).deleteTopic(id);

        topicService.deleteTopic(id);

        verify(deleteTopic).deleteTopic(id);
    }

    @Test
    public void testSearchAllPage() {
        String wordSearch = "topic";
        int page = 0;
        int size = 2;

        when(searchAllPageTopic.searchAllPage(page, size, wordSearch)).thenReturn(topicPage);
        when(getTopicResponseMapper.modelToEntity(topics.get(0))).thenReturn(expectedResponses.get(0));
        when(getTopicResponseMapper.modelToEntity(topics.get(1))).thenReturn(expectedResponses.get(1));

        Page<GetTopicResponse> result = topicService.searchAllPage(page, size, wordSearch);

        verify(searchAllPageTopic, times(1)).searchAllPage(page, size, wordSearch);
        verify(getTopicResponseMapper, times(1)).modelToEntity(topics.get(0));
        verify(getTopicResponseMapper, times(1)).modelToEntity(topics.get(1));
        assertEquals(topicResponsePage, result);
    }

    @Test
    public void testFilterTopic() {
        // Set up test data
        String description = "test";
        Integer page = 0;
        Integer size = 10;

        List<Topic> topics = new ArrayList<>();
        Topic topic1 = new Topic();
        topic1.setId(1L);
        topic1.setDescription("test 1");
        Topic topic2 = new Topic();
        topic2.setId(2L);
        topic2.setDescription("test 2");
        topics.add(topic1);
        topics.add(topic2);

        Page<Topic> topicPage = new PageImpl<>(topics);

        when(filterTopic.filter(description, page, size)).thenReturn(topicPage);

        List<GetTopicResponse> expectedResponses = new ArrayList<>();
        GetTopicResponse response1 = new GetTopicResponse();
        response1.setId(1L);
        response1.setDescription("test 1");
        GetTopicResponse response2 = new GetTopicResponse();
        response2.setId(2L);
        response2.setDescription("test 2");
        expectedResponses.add(response1);
        expectedResponses.add(response2);

        when(getTopicResponseMapper.modelToEntity(topic1)).thenReturn(response1);
        when(getTopicResponseMapper.modelToEntity(topic2)).thenReturn(response2);

        // Call the method being tested
        Page<GetTopicResponse> result = filterTopic.filter(description, page, size).map(getTopicResponseMapper::modelToEntity);

        // Verify the results
        assertEquals(expectedResponses, result.getContent());
    }

}
