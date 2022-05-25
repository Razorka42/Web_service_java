package server.test.services.resourceservice;

import server.test.services.resourceservice.resources.TestResource;

public interface ResourceService {

    void setTestResource(TestResource testResource);

    String getName();

    int getAge();
}
