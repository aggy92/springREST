package com.aggy.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping(path = "/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Kamil", "A.");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2("Kamil", "A.", 18);
    }

    @GetMapping(path = "/person", params="version=1")
    public PersonV1 getPersonV1Param() {
        return new PersonV1("Kamil", "A.");
    }

    @GetMapping(path = "/person", params="version=2")
    public PersonV2 getPersonV2Param() {
        return new PersonV2("Kamil", "A.", 18);
    }

    @GetMapping(path = "/person", headers="X-API-VERSION=1")
    public PersonV1 getPersonV1Header() {
        return new PersonV1("Kamil", "A.");
    }

    @GetMapping(path = "/person", headers="X-API-VERSION=2")
    public PersonV2 getPersonV2Header() {
        return new PersonV2("Kamil", "A.", 18);
    }
}
