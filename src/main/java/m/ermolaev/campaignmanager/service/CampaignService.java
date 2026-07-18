package m.ermolaev.campaignmanager.service;

import lombok.RequiredArgsConstructor;
import m.ermolaev.campaignmanager.entity.Campaign;
import m.ermolaev.campaignmanager.entity.EmeraldAccount;
import m.ermolaev.campaignmanager.exception.InsufficientFundsException;
import m.ermolaev.campaignmanager.repository.CampaignRepository;
import m.ermolaev.campaignmanager.repository.EmeraldAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final EmeraldAccountRepository accountRepository;

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public EmeraldAccount getAccountDetails() {
        return accountRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Emerald account not initialized"));
    }

    @Transactional
    public Campaign createCampaign(Campaign campaign) {
        EmeraldAccount account = getAccountDetails();

        if (account.getFunds().compareTo(campaign.getCampaignFund()) < 0) {
            throw new InsufficientFundsException("Not enough funds in Emerald Account. Available: " + account.getFunds());
        }

        account.setFunds(account.getFunds().subtract(campaign.getCampaignFund()));
        accountRepository.save(account);

        return campaignRepository.save(campaign);
    }

    @Transactional
    public void deleteCampaign(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        EmeraldAccount account = getAccountDetails();

        // Return funds to the account upon deletion
        account.setFunds(account.getFunds().add(campaign.getCampaignFund()));
        accountRepository.save(account);

        campaignRepository.deleteById(id);
    }

    @Transactional
    public Campaign updateCampaign(Long id, Campaign updatedData) {
        Campaign existing = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        EmeraldAccount account = getAccountDetails();

        account.setFunds(account.getFunds().add(existing.getCampaignFund()));

        if (account.getFunds().compareTo(updatedData.getCampaignFund()) < 0) {
            throw new InsufficientFundsException("Not enough funds in Emerald Account to update campaign. Available: " + account.getFunds());
        }

        // Write off the new amount and save the invoice
        account.setFunds(account.getFunds().subtract(updatedData.getCampaignFund()));
        accountRepository.save(account);

        existing.setName(updatedData.getName());
        existing.setKeywords(updatedData.getKeywords());
        existing.setBidAmount(updatedData.getBidAmount());
        existing.setCampaignFund(updatedData.getCampaignFund());
        existing.setStatus(updatedData.getStatus());
        existing.setTown(updatedData.getTown());
        existing.setRadius(updatedData.getRadius());

        return campaignRepository.save(existing);
    }
}