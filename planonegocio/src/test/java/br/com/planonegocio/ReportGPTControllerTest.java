package br.com.planonegocio;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.*;
import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.ReportGPTGatewayImpl;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller.model.GetReportGTPResponse;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.controller.model.GetReportGTPResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportGPTControllerTest {

    @Mock
    private GetAllReportGPT getAllReportGPT;

    @Mock
    private SearchAllPageReportGPT searchAllPageReportGPT;

    @Mock
    private FilterReportGPT filterReportGPT;

    @Mock
    private SaveReportGPT saveReportGPT;

    @Mock
    private UpdateReportGPT updateReportGPT;

    @Mock
    private FindOneReportGPT findOneReportGPT;

    @Mock
    private DeleteReportGPT deleteReportGPT;

    @Mock
    private GetReportGTPResponseMapper getReportGTPResponseMapper;

    @InjectMocks
    private ReportGPTGatewayImpl reportGTPGatewayImpl;

    private List<ReportGPT> mockReportGPTList;

    private Page<ReportGPT> mockReportGTPPage;

    private Page<GetReportGTPResponse> expectedResponsePage;

    private ReportGPT mockReportGPT;

    private GetReportGTPResponse expectedResponse;

    @BeforeEach
    public void setUp() {
        mockReportGPTList = Arrays.asList(new ReportGPT(), new ReportGPT(), new ReportGPT());

        mockReportGTPPage = new PageImpl<>(mockReportGPTList);

        expectedResponsePage = new PageImpl<>(
                mockReportGPTList.stream().map(reportGTP -> new GetReportGTPResponse())
                        .collect(Collectors.toList()));

        mockReportGPT = new ReportGPT();
        expectedResponse = new GetReportGTPResponse();
    }

    @Test
    public void getAllReportGTP_ShouldReturnListOfGetReportGTPResponse() {
        // given
        when(getAllReportGPT.getAllReportGPT()).thenReturn(mockReportGPTList);

        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(0))).thenReturn(new GetReportGTPResponse());
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(1))).thenReturn(new GetReportGTPResponse());
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(2))).thenReturn(new GetReportGTPResponse());

        // when
        List<GetReportGTPResponse> responseList = reportGTPGatewayImpl.getAllReportGTP();

        // then
        assertThat(responseList).isNotNull().hasSize(mockReportGPTList.size());
    }

    @Test
    public void searchAllPage_ShouldReturnPageOfGetReportGTPResponse() {
        // given
        when(searchAllPageReportGPT.searchAllPage(anyInt(), anyInt(), anyString())).thenReturn(mockReportGTPPage);

        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(0))).thenReturn(expectedResponsePage.getContent().get(0));
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(1))).thenReturn(expectedResponsePage.getContent().get(1));
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(2))).thenReturn(expectedResponsePage.getContent().get(2));

        // when
        Page<GetReportGTPResponse> responsePage = reportGTPGatewayImpl.searchAllPage(0, 10, "search");

        // then
        assertThat(responsePage).isNotNull().hasSize(mockReportGTPPage.getContent().size())
                .containsExactlyElementsOf(expectedResponsePage);
    }

    @Test
    public void filter_ShouldReturnPageOfGetReportGTPResponse() {
        // given
        when(filterReportGPT.filter(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(mockReportGTPPage);

        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(0))).thenReturn(expectedResponsePage.getContent().get(0));
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(1))).thenReturn(expectedResponsePage.getContent().get(1));
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPTList.get(2))).thenReturn(expectedResponsePage.getContent().get(2));

        // when
        Page<GetReportGTPResponse> responsePage = reportGTPGatewayImpl.filter("test", "test", "test", 0, 10);

        // then
        assertThat(responsePage).isNotNull().hasSize(mockReportGTPPage.getContent().size())
                .containsExactlyElementsOf(expectedResponsePage);
    }

    @Test
    public void saveReportGTP_ShouldReturnGetReportGTPResponse() {
        // given
        when(saveReportGPT.saveReportGPT(any(ReportGPT.class))).thenReturn(mockReportGPT);
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPT)).thenReturn(expectedResponse);

        // when
        GetReportGTPResponse response = reportGTPGatewayImpl.saveReportGTP(mockReportGPT);

        // then
        assertThat(response).isNotNull().isEqualTo(expectedResponse);
    }

    @Test
    public void updateReportGTP_ShouldReturnGetReportGTPResponse() {
        // given
        when(updateReportGPT.updateReportGPT(any(ReportGPT.class))).thenReturn(mockReportGPT);
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPT)).thenReturn(expectedResponse);

        // when
        GetReportGTPResponse response = reportGTPGatewayImpl.updateReportGTP(mockReportGPT);

        // then
        assertThat(response).isNotNull().isEqualTo(expectedResponse);
    }

    @Test
    public void findOneReportGTP_ShouldReturnGetReportGTPResponse() {
        // given
        Long mockId = 1L;
        when(findOneReportGPT.findOneReportGPT(mockId)).thenReturn(mockReportGPT);
        when(getReportGTPResponseMapper.modelToEntity(mockReportGPT)).thenReturn(expectedResponse);

        // when
        GetReportGTPResponse response = reportGTPGatewayImpl.findOneReportGTP(mockId);

        // then
        assertThat(response).isNotNull().isEqualTo(expectedResponse);
    }

    @Test
    void deleteReportGTP_ShouldCallDeleteReportGTP() {
        Long reportGTPId = 123L;
        reportGTPGatewayImpl.deleteReportGTP(reportGTPId);

        verify(deleteReportGPT).deleteReportGPT(reportGTPId);
    }
}
