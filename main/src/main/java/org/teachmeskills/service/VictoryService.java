package org.teachmeskills.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.teachmeskills.model.Organization;
import org.teachmeskills.model.Tender;
import org.teachmeskills.model.Victory;
import org.teachmeskills.repository.TenderRepository;
import org.teachmeskills.repository.VictoryRepository;

@Service
@RequiredArgsConstructor
public class VictoryService {

  private final VictoryRepository victoryRepository;
  private final TenderRepository tenderRepository;

  public void createVictory(Organization organization, Tender tender) {

    final Victory victory = Victory.builder().victoryOrganization(organization).victoryTender(tender).build();
    victoryRepository.save(victory);

    final Tender completedTender = tenderRepository.getTenderById(tender.getId());
    completedTender.setStatus(false);
    tenderRepository.save(completedTender);
  }

  public void completedTenderWithoutWinner(int tenderId){
    final Tender completedTenderWithoutWinner = tenderRepository.getTenderById(tenderId);
    completedTenderWithoutWinner.setStatus(false);
    completedTenderWithoutWinner.setStatusWithoutWinner(true);
    tenderRepository.save(completedTenderWithoutWinner);
  }
}
