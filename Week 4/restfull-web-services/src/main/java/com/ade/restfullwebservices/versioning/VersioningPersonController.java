package com.ade.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getPersonV1URI() {
        return new PersonV1("Steven Newman");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2URI() {
        return new PersonV2(new Name("Steven", "Newman"));
    }

    @GetMapping(path = "/person                     ", params = "version=1")
    public PersonV1 getPersonV1Params() {
        return new PersonV1("Steven Newman");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getPersonV2Params() {
        return new PersonV2(new Name("Steven", "Newman"));
    }

    @GetMapping(path = "/person/headers", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1Headers() {
        return new PersonV1("Steven Newman");
    }

    @GetMapping(path = "/person/headers", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2Headers() {
        return new PersonV2(new Name("Steven", "Newman"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonV1AcceptHeader() {
        return new PersonV1("Steven Newman");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonV2AcceptHeader() {
        return new PersonV2(new Name("Steven", "Newman"));
    }

}
