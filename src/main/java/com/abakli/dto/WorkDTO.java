
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
    "title",
    "edition_count",
    "cover_id",
    "cover_edition_key",
    "subject",
    "ia_collection",
    "lendinglibrary",
    "printdisabled",
    "lending_edition",
    "lending_identifier",
    "authors",
    "first_publish_year",
    "ia",
    "public_scan",
    "has_fulltext",
    "availability"
})
@Generated("jsonschema2pojo")
public class WorkDTO {

    @JsonProperty("key")
    private String key;
    @JsonProperty("title")
    private String title;
    @JsonProperty("edition_count")
    private Integer editionCount;
    @JsonProperty("cover_id")
    private Integer coverId;
    @JsonProperty("cover_edition_key")
    private String coverEditionKey;
    @JsonProperty("subject")
    private List<String> subject = null;
    @JsonProperty("ia_collection")
    private List<String> iaCollection = null;
    @JsonProperty("lendinglibrary")
    private Boolean lendinglibrary;
    @JsonProperty("printdisabled")
    private Boolean printdisabled;
    @JsonProperty("lending_edition")
    private String lendingEdition;
    @JsonProperty("lending_identifier")
    private String lendingIdentifier;
    @JsonProperty("authors")
    private List<AuthorDTO> authorDTOS = null;
    @JsonProperty("first_publish_year")
    private Integer firstPublishYear;
    @JsonProperty("ia")
    private String ia;
    @JsonProperty("public_scan")
    private Boolean publicScan;
    @JsonProperty("has_fulltext")
    private Boolean hasFulltext;
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

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("edition_count")
    public Integer getEditionCount() {
        return editionCount;
    }

    @JsonProperty("edition_count")
    public void setEditionCount(Integer editionCount) {
        this.editionCount = editionCount;
    }

    @JsonProperty("cover_id")
    public Integer getCoverId() {
        return coverId;
    }

    @JsonProperty("cover_id")
    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    @JsonProperty("cover_edition_key")
    public String getCoverEditionKey() {
        return coverEditionKey;
    }

    @JsonProperty("cover_edition_key")
    public void setCoverEditionKey(String coverEditionKey) {
        this.coverEditionKey = coverEditionKey;
    }

    @JsonProperty("subject")
    public List<String> getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    @JsonProperty("ia_collection")
    public List<String> getIaCollection() {
        return iaCollection;
    }

    @JsonProperty("ia_collection")
    public void setIaCollection(List<String> iaCollection) {
        this.iaCollection = iaCollection;
    }

    @JsonProperty("lendinglibrary")
    public Boolean getLendinglibrary() {
        return lendinglibrary;
    }

    @JsonProperty("lendinglibrary")
    public void setLendinglibrary(Boolean lendinglibrary) {
        this.lendinglibrary = lendinglibrary;
    }

    @JsonProperty("printdisabled")
    public Boolean getPrintdisabled() {
        return printdisabled;
    }

    @JsonProperty("printdisabled")
    public void setPrintdisabled(Boolean printdisabled) {
        this.printdisabled = printdisabled;
    }

    @JsonProperty("lending_edition")
    public String getLendingEdition() {
        return lendingEdition;
    }

    @JsonProperty("lending_edition")
    public void setLendingEdition(String lendingEdition) {
        this.lendingEdition = lendingEdition;
    }

    @JsonProperty("lending_identifier")
    public String getLendingIdentifier() {
        return lendingIdentifier;
    }

    @JsonProperty("lending_identifier")
    public void setLendingIdentifier(String lendingIdentifier) {
        this.lendingIdentifier = lendingIdentifier;
    }

    @JsonProperty("authors")
    public List<AuthorDTO> getAuthors() {
        return authorDTOS;
    }

    @JsonProperty("authors")
    public void setAuthors(List<AuthorDTO> authorDTOS) {
        this.authorDTOS = authorDTOS;
    }

    @JsonProperty("first_publish_year")
    public Integer getFirstPublishYear() {
        return firstPublishYear;
    }

    @JsonProperty("first_publish_year")
    public void setFirstPublishYear(Integer firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    @JsonProperty("ia")
    public String getIa() {
        return ia;
    }

    @JsonProperty("ia")
    public void setIa(String ia) {
        this.ia = ia;
    }

    @JsonProperty("public_scan")
    public Boolean getPublicScan() {
        return publicScan;
    }

    @JsonProperty("public_scan")
    public void setPublicScan(Boolean publicScan) {
        this.publicScan = publicScan;
    }

    @JsonProperty("has_fulltext")
    public Boolean getHasFulltext() {
        return hasFulltext;
    }

    @JsonProperty("has_fulltext")
    public void setHasFulltext(Boolean hasFulltext) {
        this.hasFulltext = hasFulltext;
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
