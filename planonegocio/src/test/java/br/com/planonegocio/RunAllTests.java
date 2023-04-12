package br.com.planonegocio;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ReportGPTControllerTest.class, TemplateControllerTest.class, TopicControllerTest.class})
public class RunAllTests {
}
