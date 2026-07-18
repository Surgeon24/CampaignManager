package m.ermolaev.campaignmanager.repository;

import m.ermolaev.campaignmanager.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}