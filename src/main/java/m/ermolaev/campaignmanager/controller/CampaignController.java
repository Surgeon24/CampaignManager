package m.ermolaev.campaignmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import m.ermolaev.campaignmanager.entity.Campaign;
import m.ermolaev.campaignmanager.entity.EmeraldAccount;
import m.ermolaev.campaignmanager.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    // 1. get current balance Emerald Account
    @GetMapping("/account")
    public ResponseEntity<EmeraldAccount> getAccountDetails() {
        return ResponseEntity.ok(campaignService.getAccountDetails());
    }

    // 2. list of all campaigns
    @GetMapping("/campaigns")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        return ResponseEntity.ok(campaignService.getAllCampaigns());
    }

    // 3. create new campaign
    @PostMapping("/campaigns")
    public ResponseEntity<Campaign> createCampaign(@Valid @RequestBody Campaign campaign) {
        Campaign createdCampaign = campaignService.createCampaign(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCampaign);
    }

    // 4. delete campaign
    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable("id") Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }

    // 5. update campaign
    @PutMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable("id") Long id, @Valid @RequestBody Campaign campaign) {
        return ResponseEntity.ok(campaignService.updateCampaign(id, campaign));
    }
}