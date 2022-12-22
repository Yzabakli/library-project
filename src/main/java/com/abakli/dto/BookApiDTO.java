
package com.abakli.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "name",
    "subject_type",
    "work_count",
    "works"
})
@Generated("jsonschema2pojo")
public class BookApiDTO {

    @JsonProperty("key")
    private String key;
    @JsonProperty("name")
    private String name;
    @JsonProperty("subject_type")
    private String subjectType;
    @JsonProperty("work_count")
    private Integer workCount;
    @JsonProperty("works")
    private List<WorkDTO> workDTOS = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("subject_type")
    public String getSubjectType() {
        return subjectType;
    }

    @JsonProperty("subject_type")
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    @JsonProperty("work_count")
    public Integer getWorkCount() {
        return workCount;
    }

    @JsonProperty("work_count")
    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }

    @JsonProperty("works")
    public List<WorkDTO> getWorks() {
        return workDTOS;
    }

    @JsonProperty("works")
    public void setWorks(List<WorkDTO> workDTOS) {
        this.workDTOS = workDTOS;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
