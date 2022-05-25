package server.test.services.resourceservice;

public class ResourceServerController implements ResourceServerControllerMBean {
    private ResourceService resourceService;

    public ResourceServerController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public String getName() {
        return resourceService.getName();
    }

    @Override
    public int getAge() {
        return resourceService.getAge();
    }
}
