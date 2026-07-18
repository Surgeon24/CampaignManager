import { Component, ViewChild } from '@angular/core';
import { CampaignFormComponent } from './components/campaign-form/campaign-form';
import { CampaignListComponent } from './components/campaign-list/campaign-list';
import { Campaign } from './services/api';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CampaignFormComponent, CampaignListComponent],
  templateUrl: './app.html'
})
export class App {
  @ViewChild(CampaignListComponent) listComponent!: CampaignListComponent;
  @ViewChild(CampaignFormComponent) formComponent!: CampaignFormComponent;

  onCampaignAdded() {
    if (this.listComponent) {
      this.listComponent.loadData();
    }
  }

  onEditCampaign(campaign: Campaign) {
    if (this.formComponent) {
      this.formComponent.startEdit(campaign);
    }
  }
}
