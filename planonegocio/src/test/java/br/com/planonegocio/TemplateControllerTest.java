package br.com.planonegocio;

import br.com.hackathon.planonegocio.entities.template.businessrule.*;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.TemplateGatewayImpl;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller.model.GetTemplateResponse;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller.model.GetTemplateResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TemplateControllerTest {

    @Mock
    private SaveTemplate saveTemplate;

    @Mock
    private UpdateTemplate updateTemplate;

    @Mock
    private GetAllTemplate getAllTemplate;

    @Mock
    private FindOneTemplate findOneTemplate;

    @Mock
    private DeleteTemplate deleteTemplate;

    @Mock
    private SearchAllPageTemplate searchAllPageTemplate;

    @Mock
    private FilterTemplate filterTemplate;

    @Mock
    private GetTemplateResponseMapper getTemplateResponseMapper;

    @InjectMocks
    private TemplateGatewayImpl templateService;

    private Template template;
    private Template template1;
    private Template template2;
    private GetTemplateResponse getTemplateResponse;
    private GetTemplateResponse getTemplateResponse1;
    private GetTemplateResponse getTemplateResponse2;
    private List<Template> templateList;
    private List<Template> templateList1;
    private List<GetTemplateResponse> getTemplateResponseList;
    private Page<Template> templatePage;
    private Page<GetTemplateResponse> getTemplateResponsePage;

    @BeforeEach
    public void setUp() {
        template = new Template();
        template.setId(1L);
        template.setTitle("Test Template");
        template.setDescription("This is a test template");

        getTemplateResponse = new GetTemplateResponse();
        getTemplateResponse.setId(1L);
        getTemplateResponse.setTitle("Test Template");
        getTemplateResponse.setDescription("This is a test template");

        templateList = new ArrayList<>();
        templateList.add(template);

        getTemplateResponseList = new ArrayList<>();
        getTemplateResponseList.add(getTemplateResponse);

        template1 = new Template();
        template1.setId(1L);
        template1.setTitle("Title 1");
        template1.setDescription("Description 1");

        template2 = new Template();
        template2.setId(2L);
        template2.setTitle("Title 2");
        template2.setDescription("Description 2");

        getTemplateResponse1 = new GetTemplateResponse();
        getTemplateResponse1.setId(1L);
        getTemplateResponse1.setTitle("Title 1");
        getTemplateResponse1.setDescription("Description 1");

        getTemplateResponse2 = new GetTemplateResponse();
        getTemplateResponse2.setId(2L);
        getTemplateResponse2.setTitle("Title 2");
        getTemplateResponse2.setDescription("Description 2");

        templateList1 = Arrays.asList(template1, template2);
        templatePage = new PageImpl<>(templateList1);
    }

    @Test
    public void saveTemplate_ShouldReturnGetTemplateResponse_WhenSaveTemplate() {
        when(saveTemplate.saveTemplate(any(Template.class))).thenReturn(template);
        when(getTemplateResponseMapper.modelToEntity(any(Template.class))).thenReturn(getTemplateResponse);

        GetTemplateResponse result = templateService.saveTemplate(template);

        assertThat(result).isEqualTo(getTemplateResponse);
    }

    @Test
    public void updateTemplate_ShouldReturnGetTemplateResponse_WhenUpdateTemplate() {
        when(updateTemplate.updateTemplate(any(Template.class))).thenReturn(template);
        when(getTemplateResponseMapper.modelToEntity(any(Template.class))).thenReturn(getTemplateResponse);

        GetTemplateResponse result = templateService.updateTemplate(template);

        assertThat(result).isEqualTo(getTemplateResponse);
    }

    @Test
    public void getAllTemplate_ShouldReturnGetTemplateResponseList_WhenGetAllTemplate() {
        when(getAllTemplate.getAllTemplate()).thenReturn(templateList);
        when(getTemplateResponseMapper.modelToEntity(any(Template.class))).thenReturn(getTemplateResponse);

        List<GetTemplateResponse> result = templateService.getAllTemplate();

        assertThat(result).isEqualTo(getTemplateResponseList);
    }

    @Test
    public void findOneTemplate_ShouldReturnOneTemplate_WhenFindOneTemplate() {
        when(findOneTemplate.findOneTemplate(1L)).thenReturn(template);
        when(getTemplateResponseMapper.modelToEntity(template)).thenReturn(getTemplateResponse);

        GetTemplateResponse result = templateService.findOneTemplate(1L);

        assertEquals(getTemplateResponse, result);
    }

    @Test
    void deleteTemplate_ShouldDeleteTemplateAndReturnVoid_WhenDeleteTemplate() {
        Long id = 1L;

        templateService.deleteTemplate(id);

        verify(deleteTemplate).deleteTemplate(id);
    }

    @Test
    void searchAllPage_ShouldReturnResponsePage_WhenSearchAllPageTest() {
        // Arrange
        Integer page = 1;
        Integer size = 10;
        String wordSearch = "test";
        Page<Template> templatePage = new PageImpl<>(Collections.singletonList(new Template()));
        Page<GetTemplateResponse> expectedPage = new PageImpl<>(Collections.singletonList(new GetTemplateResponse()));
        when(searchAllPageTemplate.searchAllPage(page, size, wordSearch)).thenReturn(templatePage);
        when(getTemplateResponseMapper.modelToEntity(any(Template.class))).thenReturn(new GetTemplateResponse());

        // Act
        Page<GetTemplateResponse> resultPage = templateService.searchAllPage(page, size, wordSearch);

        // Assert
        assertEquals(expectedPage, resultPage);
        verify(searchAllPageTemplate).searchAllPage(page, size, wordSearch);
        verify(getTemplateResponseMapper, atLeastOnce()).modelToEntity(any(Template.class));
    }

    @Test
    void filterTemplate_ShouldReturnTemplatesWithFilters_WhenFilter() {
        // Arrange
        String title = "Title";
        String description = "Description";
        Integer page = 1;
        Integer size = 10;

        when(filterTemplate.filter(title, description, page, size)).thenReturn(templatePage);
        when(getTemplateResponseMapper.modelToEntity(template1)).thenReturn(getTemplateResponse1);
        when(getTemplateResponseMapper.modelToEntity(template2)).thenReturn(getTemplateResponse2);

        // Act
        Page<GetTemplateResponse> result = templateService.filter(title, description, page, size);

        // Assert
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(getTemplateResponse1, result.getContent().get(0));
        assertEquals(getTemplateResponse2, result.getContent().get(1));
    }


}
