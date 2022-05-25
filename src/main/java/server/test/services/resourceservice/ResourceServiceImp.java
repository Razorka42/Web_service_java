package server.test.services.resourceservice;

import server.test.services.resourceservice.resources.TestResource;

public class ResourceServiceImp implements ResourceService {
    private TestResource testResource;

    public ResourceServiceImp(TestResource testResource) {
        this.testResource = testResource;
    }

    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }
}
