package com.airiea.agent.repository.impl;

import com.airiea.agent.common.factory.AgentFactory;
import com.airiea.agent.model.dao.AgentDAO;
import com.airiea.agent.model.dto.AgentDTO;
import com.airiea.agent.repository.AgentRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AgentRepositoryImpl implements AgentRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final AgentFactory agentFactory;

    public AgentRepositoryImpl(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = Objects.requireNonNull(dynamoDBMapper, "Mapper cannot be null");
        this.agentFactory = AgentFactory.INSTANCE;
    }

    @Override
    public AgentDTO getAgentDTOByName(String agentName) {
        final AgentDAO agentDAO = this.getAgentDAOByName(agentName);

        return agentFactory.daoToDto(agentDAO);
    }

    @Override
    public AgentDTO createAgentDTO(AgentDTO agentDTO) {
        final AgentDAO agentDAO = agentFactory.dtoToDao(agentDTO);
        this.createAgentDAO(agentDAO);

        return agentDTO;
    }
    @Override
    public AgentDTO updateAgentDTO(AgentDTO agentDTO) {
        final AgentDAO agentDAO = agentFactory.dtoToDao(agentDTO);
        this.updateAgentDAO(agentDAO);

        return agentDTO;
    }

    @Override
    public List<AgentDTO> getAllAgentDTOs() {
        List<AgentDAO> agentDAOList = this.getAllAgentDAOs();

        return agentDAOList.stream()
                .map(agentFactory::daoToDto)
                .collect(Collectors.toList());
    }




    @Override
    public AgentDAO getAgentDAOByName(String agentName) {
        return dynamoDBMapper.load(AgentDAO.class, agentName);
    }

    @Override
    public AgentDAO createAgentDAO(AgentDAO agentDAO) {
        if (!Objects.isNull(dynamoDBMapper.load(agentDAO))) {
            throw new IllegalArgumentException("Failed to create agentDAO " + agentDAO + ", item already exists.");
        }

        dynamoDBMapper.save(agentDAO);
        return agentDAO;
    }

    @Override
    public AgentDAO updateAgentDAO(AgentDAO agentDAO) {
        if (Objects.isNull(dynamoDBMapper.load(agentDAO))) {
            throw new IllegalArgumentException("Failed to update agentDAO " + agentDAO + ", item not found.");
        }

        dynamoDBMapper.save(agentDAO);
        return agentDAO;
    }

    @Override
    public List<AgentDAO> getAllAgentDAOs() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        return dynamoDBMapper.scan(AgentDAO.class, scanExpression);
    }
}
