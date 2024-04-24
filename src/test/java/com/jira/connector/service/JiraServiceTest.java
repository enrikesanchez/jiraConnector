package com.jira.connector.service;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.jira.connector.valueobject.Issue;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
class JiraServiceTest {
    @Mock
    HttpClient httpClient;

    @InjectMocks
    JiraService jiraService; // SUT

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jiraService, "jiraApiUrl", "https://localhost/rest/api/2/issue/");
        ReflectionTestUtils.setField(jiraService, "jiraApiToken", "token");
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetIssue() throws URISyntaxException, IOException, InterruptedException {
        // GIVEN
        String key = "SCRUM-1";
        Issue expectedIssue = new Issue("SCRUM-2", "IN CODING", "TEST USER STORY");

        HttpResponse<String> response = (HttpResponse<String>) Mockito.mock(HttpResponse.class);
        given(httpClient.send(any(HttpRequest.class),(HttpResponse.BodyHandler<String>) any( HttpResponse.BodyHandler.class))).willReturn(response);
        given(response.statusCode()).willReturn(200);
        given(response.body()).willReturn("{\"expand\":\"renderedFields,names,schema,operations,editmeta,changelog,versionedRepresentations,customfield_10010.requestTypePractice\",\"id\":\"10000\",\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/issue/10000\",\"key\":\"SCRUM-1\",\"fields\":{\"statuscategorychangedate\":\"2024-04-03T17:40:00.318-0600\",\"issuetype\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/issuetype/10001\",\"id\":\"10001\",\"description\":\"Stories track functionality or features expressed as user goals.\",\"iconUrl\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/universal_avatar/view/type/issuetype/avatar/10315?size=medium\",\"name\":\"Story\",\"subtask\":false,\"avatarId\":10315,\"entityId\":\"fe62aa5b-d28a-41e2-b900-4d3859dd8efa\",\"hierarchyLevel\":0},\"timespent\":null,\"project\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/project/10000\",\"id\":\"10000\",\"key\":\"SCRUM\",\"name\":\"My Scrum Project\",\"projectTypeKey\":\"software\",\"simplified\":true,\"avatarUrls\":{\"48x48\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/universal_avatar/view/type/project/avatar/10408\",\"24x24\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/universal_avatar/view/type/project/avatar/10408?size=small\",\"16x16\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/universal_avatar/view/type/project/avatar/10408?size=xsmall\",\"32x32\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/universal_avatar/view/type/project/avatar/10408?size=medium\"}},\"fixVersions\":[],\"aggregatetimespent\":null,\"resolution\":null,\"customfield_10027\":null,\"customfield_10028\":null,\"customfield_10029\":null,\"resolutiondate\":null,\"workratio\":-1,\"watches\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/issue/SCRUM-1/watchers\",\"watchCount\":1,\"isWatching\":true},\"lastViewed\":\"2024-04-04T19:45:56.039-0600\",\"issuerestriction\":{\"issuerestrictions\":{},\"shouldDisplay\":true},\"created\":\"2024-04-03T17:39:17.745-0600\",\"customfield_10020\":[{\"id\":1,\"name\":\"SCRUM Sprint 1\",\"state\":\"active\",\"boardId\":1,\"goal\":\"\",\"startDate\":\"2024-04-03T23:39:53.626Z\",\"endDate\":\"2024-04-17T23:39:50.000Z\"}],\"customfield_10021\":null,\"customfield_10022\":null,\"customfield_10023\":null,\"priority\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/priority/3\",\"iconUrl\":\"https://tec-team-i94t1m1u.atlassian.net/images/icons/priorities/medium.svg\",\"name\":\"Medium\",\"id\":\"3\"},\"customfield_10024\":null,\"customfield_10025\":null,\"labels\":[],\"customfield_10026\":null,\"customfield_10016\":null,\"customfield_10017\":null,\"customfield_10018\":{\"hasEpicLinkFieldDependency\":false,\"showField\":false,\"nonEditableReason\":{\"reason\":\"PLUGIN_LICENSE_ERROR\",\"message\":\"The Parent Link is only available to Jira Premium users.\"}},\"customfield_10019\":\"0|hzzzzz:\",\"timeestimate\":null,\"aggregatetimeoriginalestimate\":null,\"versions\":[],\"issuelinks\":[],\"assignee\":null,\"updated\":\"2024-04-03T17:40:00.317-0600\",\"status\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/status/10005\",\"description\":\"\",\"iconUrl\":\"https://tec-team-i94t1m1u.atlassian.net/\",\"name\":\"IN CODING\",\"id\":\"10005\",\"statusCategory\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/statuscategory/4\",\"id\":4,\"key\":\"indeterminate\",\"colorName\":\"yellow\",\"name\":\"In Progress\"}},\"components\":[],\"timeoriginalestimate\":null,\"description\":null,\"customfield_10010\":null,\"customfield_10014\":null,\"timetracking\":{},\"customfield_10015\":null,\"customfield_10005\":null,\"customfield_10006\":null,\"security\":null,\"customfield_10007\":null,\"customfield_10008\":null,\"attachment\":[],\"aggregatetimeestimate\":null,\"customfield_10009\":null,\"summary\":\"TEST USER STORY\",\"creator\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/user?accountId=5d047cc532074c0c50d035b8\",\"accountId\":\"5d047cc532074c0c50d035b8\",\"emailAddress\":\"enrike.sanchez@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\"},\"displayName\":\"Enrique Antonio Sánchez Ordóñez\",\"active\":true,\"timeZone\":\"America/Chihuahua\",\"accountType\":\"atlassian\"},\"subtasks\":[],\"reporter\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/user?accountId=5d047cc532074c0c50d035b8\",\"accountId\":\"5d047cc532074c0c50d035b8\",\"emailAddress\":\"enrike.sanchez@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/254b2c5ca68a9365ee32eadd7b5b5b72?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FEO-0.png\"},\"displayName\":\"Enrique Antonio Sánchez Ordóñez\",\"active\":true,\"timeZone\":\"America/Chihuahua\",\"accountType\":\"atlassian\"},\"aggregateprogress\":{\"progress\":0,\"total\":0},\"customfield_10001\":null,\"customfield_10002\":null,\"customfield_10003\":null,\"customfield_10004\":null,\"environment\":null,\"duedate\":null,\"progress\":{\"progress\":0,\"total\":0},\"votes\":{\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/issue/SCRUM-1/votes\",\"votes\":0,\"hasVoted\":false},\"comment\":{\"comments\":[],\"self\":\"https://tec-team-i94t1m1u.atlassian.net/rest/api/2/issue/10000/comment\",\"maxResults\":0,\"total\":0,\"startAt\":0},\"worklog\":{\"startAt\":0,\"maxResults\":20,\"total\":0,\"worklogs\":[]}}}");

        // WHEN
        Issue issue = jiraService.getIssue(key);

        //THEN
        assertEquals(expectedIssue, issue);
    }
}
