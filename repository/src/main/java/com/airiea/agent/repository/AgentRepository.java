package com.airiea.agent.repository;

import com.airiea.agent.model.dao.AgentDAO;
import com.airiea.agent.model.dto.AgentDTO;

import java.util.List;

/**
 * Data Access Object (DAO) interface for tasks.
 * This provides methods to perform operations on tasks.
 */
public interface AgentRepository {
    AgentDTO getAgentDTOByName(String agentName);
    AgentDTO createAgentDTO(AgentDTO agent);
    AgentDTO updateAgentDTO(AgentDTO agent);
    List<AgentDTO> getAllAgentDTOs();

    AgentDAO getAgentDAOByName(String agentName);
    AgentDAO createAgentDAO(AgentDAO agentDAO);
    AgentDAO updateAgentDAO(AgentDAO agentDAO);
    List<AgentDAO> getAllAgentDAOs();
}
