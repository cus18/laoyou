package com.yhl.laoyou.common.dto.practitioner.chronic;

import com.alibaba.fastjson.annotation.JSONField;

public class ChronicDTO {

    @JSONField(name = "chronicId")
    private String chronicId;

    @JSONField(name = "chronicName")
    private String chronicName;

    @JSONField(name = "chronicDescription")
    private String chronicDescription;

    public String getChronicId() {
        return chronicId;
    }

    public void setChronicId(String chronicId) {
        this.chronicId = chronicId;
    }

    public String getChronicName() {
        return chronicName;
    }

    public void setChronicName(String chronicName) {
        this.chronicName = chronicName;
    }

    public String getChronicDescription() {
        return chronicDescription;
    }

    public void setChronicDescription(String chronicDescription) {
        this.chronicDescription = chronicDescription;
    }
}