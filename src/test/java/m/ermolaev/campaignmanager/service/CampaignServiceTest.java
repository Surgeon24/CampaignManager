package m.ermolaev.campaignmanager.service;

import m.ermolaev.campaignmanager.entity.Campaign;
import m.ermolaev.campaignmanager.entity.EmeraldAccount;
import m.ermolaev.campaignmanager.exception.InsufficientFundsException;
import m.ermolaev.campaignmanager.repository.CampaignRepository;
import m.ermolaev.campaignmanager.repository.EmeraldAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private EmeraldAccountRepository accountRepository;

    @InjectMocks
    private CampaignService campaignService;

    private EmeraldAccount testAccount;
    private Campaign testCampaign;

    @BeforeEach
    void setUp() {
        testAccount = new EmeraldAccount();
        testAccount.setId(1L);
        testAccount.setFunds(new BigDecimal("1000.00"));

        testCampaign = new Campaign();
        testCampaign.setId(1L);
        testCampaign.setName("Black Friday Sale");
        testCampaign.setCampaignFund(new BigDecimal("200.00"));
    }

    @Test
    void createCampaign_SuccessfulDeduction() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(testCampaign);

        Campaign created = campaignService.createCampaign(testCampaign);

        assertNotNull(created);
        assertEquals(new BigDecimal("800.00"), testAccount.getFunds());

        verify(accountRepository).save(testAccount);
        verify(campaignRepository).save(testCampaign);
    }

    @Test
    void createCampaign_InsufficientFunds_ThrowsException() {
        testCampaign.setCampaignFund(new BigDecimal("2000.00"));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));

        assertThrows(InsufficientFundsException.class, () -> {
            campaignService.createCampaign(testCampaign);
        });

        verify(campaignRepository, never()).save(any());
    }

    @Test
    void deleteCampaign_FundsReturned() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(testCampaign));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));

        campaignService.deleteCampaign(1L);

        assertEquals(new BigDecimal("1200.00"), testAccount.getFunds());
        verify(accountRepository).save(testAccount);
        verify(campaignRepository).deleteById(1L);
    }
}