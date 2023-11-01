package com.airiea.agent.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"agentName"})
public class AgentDTO {
    @JsonProperty("agent_name")
    String agentName;

    @JsonProperty("agent_role")
    String agentRole;

    @JsonProperty("agent_goal")
    String agentGoal;

    @JsonProperty("ability_name")
    String abilityName;

    @JsonProperty("updatedDate")
    Date updatedDate;

    @JsonProperty("created_date")
    Date createdDate;
}
