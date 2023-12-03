package com.ow.votingapp.repository;

import com.ow.votingapp.model.entity.VoterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoterRepository extends JpaRepository<VoterEntity, Long> {

    Optional<VoterEntity> findByIdentificationNumber(final UUID identificationNumber);

}
