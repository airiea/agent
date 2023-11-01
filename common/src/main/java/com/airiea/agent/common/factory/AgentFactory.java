package com.airiea.agent.common.factory;

import com.airiea.agent.model.dao.AgentDAO;
import com.airiea.agent.model.dto.AgentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgentFactory {
    AgentFactory INSTANCE = Mappers.getMapper(AgentFactory.class);
    AgentDTO daoToDto(AgentDAO agentDAO);
    AgentDAO dtoToDao(AgentDTO agentDTO);
}
