package com.mogakko.be_final.domain.sse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class RelatedUrl {
    @Column(nullable = false)
    private String url;

    public RelatedUrl(String url) {
        this.url = url;
    }
}
